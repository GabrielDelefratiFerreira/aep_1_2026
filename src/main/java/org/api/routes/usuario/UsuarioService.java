package org.api.routes.usuario;

import org.api.core.ApiException;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository repositoryUsuario;

    public UsuarioService() {
        this.repositoryUsuario = new UsuarioRepository();
    }

    public UsuarioModel create(UsuarioModel usuario) throws ApiException {
        if (usuario.getId() != null)
            throw ApiException.unauthorized("Do not send id to create user");
        return repositoryUsuario.save(usuario);
    }

    public List<UsuarioModel> findAll() {
        return repositoryUsuario.findAll();
    }

    public void update(Long id, UsuarioModel usuario) throws ApiException {
        if (id != usuario.getId())
            throw ApiException.unauthorized("Could not update data from another user");
        this.repositoryUsuario.update(usuario);
    }

    public void revoke(Long id) {
        this.repositoryUsuario.revoke(id);
    }

    public UsuarioModel byId(Long id) throws ApiException {
        List<UsuarioModel> usuarios = this.repositoryUsuario.findAll();
        for (UsuarioModel item : usuarios) {
            if (item.getId().equals(id))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }

    public UsuarioModel byEmail(String email) throws ApiException {
        List<UsuarioModel> usuarios = this.repositoryUsuario.findAll();
        for (UsuarioModel item : usuarios) {
            if (item.getEmail().equals(email))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
