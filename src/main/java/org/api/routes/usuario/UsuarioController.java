package org.api.routes.usuario;

import org.api.core.ApiServer;
import org.api.core.JsonMapper;
import org.api.core.Enum.EnumAccessModifier;
import org.api.core.ApiController;
import org.api.core.ApiException;
import org.api.core.ApiResponse;
import java.util.List;
import java.util.ArrayList;
import com.sun.net.httpserver.HttpExchange;

public class UsuarioController extends ApiController {
  UsuarioService service;

  public UsuarioController(ApiServer server, UsuarioService service) throws ApiException {
    super("usuario", server);
    this.service = service;
    this.delete(this::revoke, EnumAccessModifier.PRIVATE);
    this.put(this::update, "/:id", EnumAccessModifier.PRIVATE);
    this.get(this::byId, "/by-id/:id", EnumAccessModifier.PRIVATE);
    this.get(this::findAll, EnumAccessModifier.PRIVATE);
  }

  public ApiResponse update(HttpExchange req) throws ApiException {
    Long id = this.extractId(req);
    UsuarioModel usuario = this.extractBody(req, UsuarioModel.class);
    usuario.setId(id);
    this.service.update(id, usuario);
    return ApiResponse.noContent("Succsessfully updated!");
  }

  public ApiResponse revoke(HttpExchange req) throws ApiException {
    Long id = super.extractId(req);
    this.service.revoke(id);
    return ApiResponse.noContent("Succsessfully deleted!");
  }

  public ApiResponse byId(HttpExchange req) throws ApiException {
    Long id = super.extractId(req);
    return ApiResponse.ok(JsonMapper.toJson(this.service.byId(id)));
  }

  public ApiResponse findAll(HttpExchange req) throws ApiException {
    List<String> solicitacoes = new ArrayList<>();
    for (UsuarioModel s : this.service.findAll()) {
      solicitacoes.add(JsonMapper.toJson(s));
    }
    return ApiResponse.ok(solicitacoes.toString());
  }
}
