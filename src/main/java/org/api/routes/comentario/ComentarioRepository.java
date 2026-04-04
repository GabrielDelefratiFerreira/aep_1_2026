package org.api.routes.comentario;

import java.util.ArrayList;
import java.util.List;

public class ComentarioRepository {
  private List<ComentarioModel> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public ComentarioModel save(ComentarioModel comentario) {
    if (comentario.getId() == null)
      comentario.setId(getNewId());
    banco.add(comentario);
    return comentario;
  }

  public void update(ComentarioModel comentario) {
    for (int i = 0; i < banco.size(); i++) {
      if (banco.get(i).getId().equals(comentario.getId())) {
        banco.set(i, comentario);
        return;
      }
    }
  }

  public List<ComentarioModel> findAll() {
    return this.banco.stream().toList();
  }

  public void revoke(Long id) {
    banco.removeIf((s) -> s.getId().equals(id));
  }

  private Long getNewId() {
    return ++this.idIndex;
  }
}
