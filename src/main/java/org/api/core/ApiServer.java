package org.api.core;

import org.api.core.Enum.EnumMethod;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.*;

public class ApiServer {
    private static int PORT = 8080;
    private HttpServer builder;
    private final List<RouteEntry> routes = new ArrayList<>();

    private record RouteEntry(EnumMethod method, String pattern, RouteHandler handler) {
    }

    public ApiServer() throws IOException {
        this.builder = HttpServer.create(new InetSocketAddress(PORT), 0);
    }

    @FunctionalInterface
    public interface RouteHandler {
        ApiResponse handle(HttpExchange exchange) throws ApiException;
    }

    public void run() {
        this.builder.createContext("/", (exch) -> {
            String requestPath = exch.getRequestURI().getPath();
            String requestMethod = exch.getRequestMethod();

            for (RouteEntry entry : routes) {
                if (!entry.method().name().equalsIgnoreCase(requestMethod))
                    continue;
                Map<String, String> pathParams = matchAndExtract(entry.pattern(), requestPath);

                if (pathParams == null)
                    continue;
                exch.setAttribute("pathParams", pathParams);

                try {
                    ApiResponse res = entry.handler().handle(exch);
                    String mssg = res.getMessage();
                    byte[] bytes = mssg.getBytes();
                    exch.sendResponseHeaders(res.getStatusCode(), bytes.length);
                    try (OutputStream os = exch.getResponseBody()) {
                        os.write(bytes);
                    }
                } catch (ApiException e) {
                    System.out.println(e);
                    byte[] bytes = e.getMessage().getBytes();
                    exch.sendResponseHeaders(e.getStatusCode(), bytes.length);
                    try (OutputStream os = exch.getResponseBody()) {
                        os.write(bytes);
                    }
                }
                return;
            }

            exch.sendResponseHeaders(404, -1);
        });

        this.builder.setExecutor(null);
        System.out.println("Running server at " + PORT);
        this.builder.start();
    }

    public void attachRoute(RouteHandler handler, EnumMethod method, String route) {
        System.out.println("=======");
        System.out.println("ROUTE:  " + route);
        System.out.println("METHOD: " + method);
        routes.add(new RouteEntry(method, route, handler));
    }

    private Map<String, String> matchAndExtract(String pattern, String path) {
        String[] patternParts = pattern.split("/");
        String[] pathParts = path.split("/");
        if (patternParts.length != pathParts.length)
            return null;

        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < patternParts.length; i++) {
            if (patternParts[i].startsWith(":"))
                params.put(patternParts[i].substring(1), pathParts[i]);
            else if (!patternParts[i].equals(pathParts[i]))
                return null;
        }
        return params;
    }
}
