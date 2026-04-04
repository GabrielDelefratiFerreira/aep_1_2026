package org.api.routes.auth;

import java.io.IOException;
import org.api.core.ApiController;
import org.api.core.ApiException;
import org.api.core.ApiResponse;
import org.api.core.ApiServer;
import org.api.core.JsonMapper;
import org.api.core.Enum.EnumAccessModifier;
import org.api.routes.usuario.UsuarioModel;
import org.api.routes.usuario.UsuarioService;
import org.api.routes.token.TokenService;
import com.sun.net.httpserver.HttpExchange;

public class AuthController extends ApiController {
  private final TokenService tokenService;
  private final UsuarioService userService;

  public AuthController(ApiServer server, TokenService tokenService, UsuarioService userService) throws ApiException {
    super("auth", server);
    this.tokenService = tokenService;
    this.userService = userService;
    this.post(this::login, "login", EnumAccessModifier.PUBLIC);
    this.post(this::signup, "signup", EnumAccessModifier.PUBLIC);
  }

  public ApiResponse login(HttpExchange exchange) throws ApiException {
    try {
      String body = new String(exchange.getRequestBody().readAllBytes());
      AuthModel req = JsonMapper.fromJson(body, AuthModel.class);
      UsuarioModel user = this.userService.byEmail(req.getEmail());
      if (user == null || !user.getPassword().equals(req.getPassword()))
        throw ApiException.unauthorized("Invalid credentials!");
      return ApiResponse.ok(handleAuthenticate(user));
    } catch (ApiException e) {
      throw e;
    } catch (IOException e) {
      throw ApiException.unauthorized("Could not authenticate");
    }
  }

  public ApiResponse signup(HttpExchange req) throws ApiException {
    UsuarioModel usuario = this.extractBody(req, UsuarioModel.class);
    UsuarioModel user = this.userService.create(usuario);
    return ApiResponse.ok(handleAuthenticate(user));
  }

  private String handleAuthenticate(UsuarioModel user) throws ApiException {
    String token = tokenService.generateToken(user.getId());
    return "{\"bearer\":\"" + token + "\"}";
  }
}
