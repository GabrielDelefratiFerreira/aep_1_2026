package org.api.routes.comentario;

import org.api.core.ApiException;
import java.util.List;

public class ComentarioService {
    private ComentarioRepository repositoryComentario;

    public ComentarioService() {
        this.repositoryComentario = new ComentarioRepository();
    }

    public ComentarioModel create(ComentarioModel comentario) throws ApiException {
        if (comentario.getId() != null)
            throw ApiException.unauthorized("Do not send id to create comentario");
        return repositoryComentario.save(comentario);
    }

    public List<ComentarioModel> findAll() {
        return repositoryComentario.findAll();
    }

    public void update(Long id, ComentarioModel comentario) throws ApiException {
        if (id != comentario.getId())
            throw ApiException.unauthorized("Could not update data from another comentario");
        this.repositoryComentario.update(comentario);
    }

    public void revoke(Long id) {
        this.repositoryComentario.revoke(id);
    }

    public ComentarioModel byId(Long id) throws ApiException {
        List<ComentarioModel> comentarios = this.repositoryComentario.findAll();
        for (ComentarioModel item : comentarios) {
            if (item.getId().equals(id))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
