package org.api.routes.usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
  private List<UsuarioModel> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public UsuarioModel save(UsuarioModel usuario) {
    if (usuario.getId() == null)
      usuario.setId(getNewId());
    banco.add(usuario);
    return usuario;
  }

  public void update(UsuarioModel usuario) {
    for (int i = 0; i < banco.size(); i++) {
      if (banco.get(i).getId().equals(usuario.getId())) {
        banco.set(i, usuario);
        return;
      }
    }
  }

  public List<UsuarioModel> findAll() {
    return this.banco.stream().toList();
  }

  public void revoke(Long id) {
    banco.removeIf((s) -> s.getId().equals(id));
  }

  private Long getNewId() {
    return ++this.idIndex;
  }
}
