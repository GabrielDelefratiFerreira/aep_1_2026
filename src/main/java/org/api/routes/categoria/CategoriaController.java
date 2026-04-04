package org.api.routes.categoria;

import java.util.ArrayList;
import java.util.List;
import org.api.core.ApiController;
import org.api.core.ApiException;
import org.api.core.ApiResponse;
import org.api.core.ApiServer;
import org.api.core.JsonMapper;
import org.api.core.Enum.EnumAccessModifier;
import com.sun.net.httpserver.HttpExchange;

public class CategoriaController extends ApiController {
  private CategoriaService service = new CategoriaService();

  public CategoriaController(
      ApiServer server)
      throws ApiException {
    super("categoria", server);
    this.service = new CategoriaService();
    this.delete(this::revoke, EnumAccessModifier.PRIVATE);
    this.put(this::update, "/:id", EnumAccessModifier.PRIVATE);
    this.get(this::byId, "/by-id/:id", EnumAccessModifier.PUBLIC);
    this.get(this::findAll, EnumAccessModifier.PUBLIC);
    this.post(this::create, EnumAccessModifier.PRIVATE);
  }

  public ApiResponse create(HttpExchange req) throws ApiException {
    CategoriaModel categoria = this.extractBody(req, CategoriaModel.class);
    this.service.create(categoria);
    return ApiResponse.ok("Succsessfully created!");
  }

  public ApiResponse update(HttpExchange req) throws ApiException {
    Long id = this.extractId(req);
    CategoriaModel categoria = this.extractBody(req, CategoriaModel.class);
    categoria.setId(id);
    this.service.update(id, categoria);
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
    for (CategoriaModel s : this.service.findAll()) {
      solicitacoes.add(JsonMapper.toJson(s));
    }
    return ApiResponse.ok(solicitacoes.toString());
  }
}
