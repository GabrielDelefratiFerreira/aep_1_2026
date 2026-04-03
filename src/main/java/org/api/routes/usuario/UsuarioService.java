package org.api.routes.usuario;

import org.api.core.ApiException;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository repositoryUsuario;

    public UsuarioService() {
        this.repositoryUsuario = new UsuarioRepository();
    }

    public void create(UsuarioModel usuario) {
        repositoryUsuario.save(usuario);
    }

    public List<UsuarioModel> findAll() {
        return repositoryUsuario.findAll();
    }

    public void update(Long id, UsuarioModel usuario) {
        usuario.setId(id);
        this.repositoryUsuario.update(usuario);
    }

    public void revoke(Long id) {
        this.repositoryUsuario.revoke(id);
    }

    public UsuarioModel byId(Long id) throws ApiException {
        List<UsuarioModel> solicitacoes = this.repositoryUsuario.findAll();
        for (UsuarioModel item : solicitacoes) {
            if (item.getId().equals(id)) return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
