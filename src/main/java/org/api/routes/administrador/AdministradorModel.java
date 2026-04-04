package org.api.routes.administrador;

import org.api.core.Enum.EnumAccessLevel;
import org.api.routes.usuario.UsuarioModel;

public class AdministradorModel extends UsuarioModel {
    EnumAccessLevel accessLevel;

    public AdministradorModel(Long id, String nome, String email, String password, EnumAccessLevel accsLevel) {
        super(id, nome, email, password);
        this.accessLevel = accsLevel;
    }
}
