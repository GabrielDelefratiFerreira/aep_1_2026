package org.api.routes.solicitacao;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepository {
    private List<SolicitacaoModel> banco = new ArrayList<>();
    private Long idIndex = 0L;

    public void save(SolicitacaoModel solicitacao) {
        if (solicitacao.getId() == null)
            solicitacao.setId(getNewId());
        banco.add(solicitacao);
    }

    public void update(SolicitacaoModel solicitacao) {
        for (int i = 0; i < banco.size(); i++) {
            if (banco.get(i).getId().equals(solicitacao.getId())) {
                banco.set(i, solicitacao);
                return;
            }
        }
    }

    public List<SolicitacaoModel> findAll() {
        return this.banco.stream().toList();
    }

    public void revoke(Long id) {
        banco.removeIf((s) -> s.getId() == id);
    }

    private Long getNewId() {
        this.idIndex++;
        return this.idIndex;
    }
}
