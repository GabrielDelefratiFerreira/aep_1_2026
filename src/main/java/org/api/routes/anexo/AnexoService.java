package org.api.routes.anexo;

import org.api.core.ApiException;
import java.util.List;

public class AnexoService {
    private AnexoRepository repositoryAnexo;

    public AnexoService() {
        this.repositoryAnexo = new AnexoRepository();
    }

    public AnexoModel create(AnexoModel anexo) throws ApiException {
        if (anexo.getId() != null)
            throw ApiException.unauthorized("Do not send id to create anexo");
        return repositoryAnexo.save(anexo);
    }

    public List<AnexoModel> findAll() {
        return repositoryAnexo.findAll();
    }

    public void update(Long id, AnexoModel anexo) throws ApiException {
        if (id != anexo.getId())
            throw ApiException.unauthorized("Could not update data from another anexo");
        this.repositoryAnexo.update(anexo);
    }

    public void revoke(Long id) {
        this.repositoryAnexo.revoke(id);
    }

    public AnexoModel byId(Long id) throws ApiException {
        List<AnexoModel> anexos = this.repositoryAnexo.findAll();
        for (AnexoModel item : anexos) {
            if (item.getId().equals(id))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}

