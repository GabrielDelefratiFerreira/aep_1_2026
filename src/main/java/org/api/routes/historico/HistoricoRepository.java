package org.api.routes.historico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.api.core.ApiException;

public class HistoricoRepository {
  private List<HistoricoStatusModel> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public HistoricoStatusModel save(HistoricoStatusModel historico) {
    if (historico.getId() == null)
      historico.setId(getNewId());
    banco.add(historico);
    return historico;
  }

  public void update(HistoricoStatusModel historico) {
    for (int i = 0; i < banco.size(); i++) {
      if (banco.get(i).getId().equals(historico.getId())) {
        banco.set(i, historico);
        return;
      }
    }
  }

  public List<HistoricoStatusModel> findAll(Long solicitacaoId) {
    return this.banco.stream()
        .filter(historico -> historico.getIdSolicitacao().equals(solicitacaoId))
        .toList();
  }

  public HistoricoStatusModel findById(Long id) throws ApiException {
    Optional<HistoricoStatusModel> hist = this.banco.stream()
        .filter(h -> h.getId().equals(id))
        .findFirst();
    if (!hist.isPresent())
      throw ApiException.notFound("History not found");
    return hist.get();
  }

  public void revoke(Long id) {
    banco.removeIf((s) -> s.getId().equals(id));
  }

  private Long getNewId() {
    return ++this.idIndex;
  }
}
