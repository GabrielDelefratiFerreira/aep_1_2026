package org.api.routes.administrador;

import org.api.routes.usuario.UsuarioModel;

public class AdministradorModel extends UsuarioModel {
    public AdministradorModel(Long id, String nome, String email) {
        super(id, nome, email);
    }
}
