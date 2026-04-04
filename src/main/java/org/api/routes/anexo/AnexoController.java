package org.api.routes.anexo;

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

public class AnexoController extends ApiController {
  private AnexoService service = new AnexoService();
  private SolicitacaoService solicitacaoService;

  public AnexoController(
      ApiServer server,
      SolicitacaoService solicitacaoService)
      throws ApiException {
    super("anexo", server);
    this.solicitacaoService = solicitacaoService;
    this.service = new AnexoService();
    this.delete(this::revoke, EnumAccessModifier.PRIVATE);
    this.put(this::update, "/:id", EnumAccessModifier.PRIVATE);
    this.get(this::byId, "/by-id/:id", EnumAccessModifier.PUBLIC);
    this.get(this::findAll, EnumAccessModifier.PUBLIC);
    this.post(this::create, EnumAccessModifier.PRIVATE);
  }

  public ApiResponse create(HttpExchange req) throws ApiException {
    AnexoModel anexo = this.extractBody(req, AnexoModel.class);
    if ((this.solicitacaoService.byId(anexo.getSolicitacaoId())) == null)
      throw ApiException.badRequest("Could not found solicitacao by id");

    this.service.create(anexo);
    return ApiResponse.ok("Succsessfully created!");
  }

  public ApiResponse update(HttpExchange req) throws ApiException {
    Long id = this.extractId(req);
    AnexoModel anexo = this.extractBody(req, AnexoModel.class);
    anexo.setId(id);
    this.service.update(id, anexo);
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
    for (AnexoModel s : this.service.findAll()) {
      solicitacoes.add(JsonMapper.toJson(s));
    }
    return ApiResponse.ok(solicitacoes.toString());
  }
}
