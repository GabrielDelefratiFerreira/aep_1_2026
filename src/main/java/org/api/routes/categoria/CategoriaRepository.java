package org.api.routes.categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {
  private List<CategoriaModel> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public CategoriaModel save(CategoriaModel categoria) {
    if (categoria.getId() == null)
      categoria.setId(getNewId());
    banco.add(categoria);
    return categoria;
  }

  public void update(CategoriaModel categoria) {
    for (int i = 0; i < banco.size(); i++) {
      if (banco.get(i).getId().equals(categoria.getId())) {
        banco.set(i, categoria);
        return;
      }
    }
  }

  public List<CategoriaModel> findAll() {
    return this.banco.stream().toList();
  }

  public void revoke(Long id) {
    banco.removeIf((s) -> s.getId().equals(id));
  }

  private Long getNewId() {
    return ++this.idIndex;
  }
}
