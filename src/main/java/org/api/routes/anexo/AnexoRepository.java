package org.api.routes.anexo;

import java.util.ArrayList;
import java.util.List;

public class AnexoRepository {
  private List<AnexoModel> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public AnexoModel save(AnexoModel anexo) {
    if (anexo.getId() == null)
      anexo.setId(getNewId());
    banco.add(anexo);
    return anexo;
  }

  public void update(AnexoModel anexo) {
    for (int i = 0; i < banco.size(); i++) {
      if (banco.get(i).getId().equals(anexo.getId())) {
        banco.set(i, anexo);
        return;
      }
    }
  }

  public List<AnexoModel> findAll() {
    return this.banco.stream().toList();
  }

  public void revoke(Long id) {
    banco.removeIf((s) -> s.getId().equals(id));
  }

  private Long getNewId() {
    return ++this.idIndex;
  }
}
