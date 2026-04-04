package org.api.core;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import org.api.core.ApiServer.RouteHandler;
import org.api.core.Enum.EnumAccessModifier;
import org.api.core.Enum.EnumMethod;
import com.sun.net.httpserver.HttpExchange;

public class ApiController {
  private String base_url;
  private ApiServer server;

  public ApiController(String base, ApiServer server) throws ApiException{
    this.base_url = base;
    this.server = server;
  }

  public void post(RouteHandler handler, String route, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.POST, url(route), mod);
  }

  public void post(RouteHandler handler, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.POST, url(""), mod);
  }

  public void get(RouteHandler handler, String route, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.GET, url(route),mod);
  }

  public void get(RouteHandler handler, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.GET, url(""), mod);
  }

  public void delete(RouteHandler handler, String route, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.DELETE, url(route), mod);
  }

  public void delete(RouteHandler handler, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.DELETE, url(""), mod);
  }

  public void put(RouteHandler handler, String route, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.PUT, url(route), mod);
  }

  public void put(RouteHandler handler, EnumAccessModifier mod) {
    server.attachRoute(handler, EnumMethod.PUT, url(""), mod);
  }

  private String url(String url) {
    if (url.startsWith("/"))
      return '/' + this.base_url + url;
    return '/' + this.base_url + (url.equals("") ? "" : "/") + url;
  }

  public Long extractId(HttpExchange req) {
    Object raw = req.getAttribute("pathParams");
    if (!(raw instanceof Map<?, ?> map))
      return null;
    Object id = map.get("id");
    if (!(id instanceof String s))
      return null;
    try {
      return Long.parseLong(s);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  public <T extends JsonMapper> T extractBody(HttpExchange req, Class<T> entityType) throws ApiException {
    try {
      InputStream rawBody = req.getRequestBody();
      String json;

      try (Scanner scanner = new Scanner(rawBody, StandardCharsets.UTF_8.name())) {
        json = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        T entity = JsonMapper.fromJson(json, entityType);
        return entity;
      } catch (Exception e) {
        throw ApiException.badRequest("No valid body was provided");
      }
    } catch (ApiException e) {
      throw ApiException.badRequest("at body extraction: " + e);
    }
  }
}
