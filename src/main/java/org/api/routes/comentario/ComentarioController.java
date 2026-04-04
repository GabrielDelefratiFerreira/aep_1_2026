package org.api.routes.comentario;

import java.util.ArrayList;
import java.util.List;
import org.api.core.ApiController;
import org.api.core.ApiException;
import org.api.core.ApiResponse;
import org.api.core.ApiServer;
import org.api.core.JsonMapper;
import org.api.core.Enum.EnumAccessModifier;
import org.api.routes.solicitacao.SolicitacaoService;
import org.api.routes.usuario.UsuarioService;

import com.sun.net.httpserver.HttpExchange;

public class ComentarioController extends ApiController {
  private ComentarioService service = new ComentarioService();
  private SolicitacaoService solicitacaoService;
  private UsuarioService userService;

  public ComentarioController(
      ApiServer server,
      SolicitacaoService solicitacaoService,
      UsuarioService userService)
      throws ApiException {
    super("comentario", server);
    this.solicitacaoService = solicitacaoService;
    this.userService = userService;
    this.service = new ComentarioService();
    this.delete(this::revoke, EnumAccessModifier.PRIVATE);
    this.put(this::update, "/:id", EnumAccessModifier.PRIVATE);
    this.get(this::byId, "/by-id/:id", EnumAccessModifier.PUBLIC);
    this.get(this::findAll, EnumAccessModifier.PUBLIC);
    this.post(this::create, EnumAccessModifier.PRIVATE);
  }

  public ApiResponse create(HttpExchange req) throws ApiException {
    ComentarioModel comentario = this.extractBody(req, ComentarioModel.class);
    if ((this.solicitacaoService.byId(comentario.getSolicitacaoId())) == null)
      throw ApiException.badRequest("Could not found solicitacao by id");
    if ((this.userService.byId(comentario.getUserId())) == null)
      throw ApiException.badRequest("Could not found user by id");

    this.service.create(comentario);
    return ApiResponse.ok("Succsessfully created!");
  }

  public ApiResponse update(HttpExchange req) throws ApiException {
    Long id = this.extractId(req);
    ComentarioModel comentario = this.extractBody(req, ComentarioModel.class);
    comentario.setId(id);
    this.service.update(id, comentario);
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
    for (ComentarioModel s : this.service.findAll()) {
      solicitacoes.add(JsonMapper.toJson(s));
    }
    return ApiResponse.ok(solicitacoes.toString());
  }
}
