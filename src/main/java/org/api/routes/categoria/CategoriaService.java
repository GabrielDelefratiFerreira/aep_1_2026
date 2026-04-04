package org.api.routes.categoria;

import org.api.core.ApiException;
import java.util.List;

public class CategoriaService {
    private CategoriaRepository repositoryCategoria;

    public CategoriaService() {
        this.repositoryCategoria = new CategoriaRepository();
    }

    public CategoriaModel create(CategoriaModel categoria) throws ApiException {
        if (categoria.getId() != null)
            throw ApiException.unauthorized("Do not send id to create categoria");
        return repositoryCategoria.save(categoria);
    }

    public List<CategoriaModel> findAll() {
        return repositoryCategoria.findAll();
    }

    public void update(Long id, CategoriaModel categoria) throws ApiException {
        if (id != categoria.getId())
            throw ApiException.unauthorized("Could not update data from another categoria");
        this.repositoryCategoria.update(categoria);
    }

    public void revoke(Long id) {
        this.repositoryCategoria.revoke(id);
    }

    public CategoriaModel byId(Long id) throws ApiException {
        List<CategoriaModel> categorias = this.repositoryCategoria.findAll();
        for (CategoriaModel item : categorias) {
            if (item.getId().equals(id))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
