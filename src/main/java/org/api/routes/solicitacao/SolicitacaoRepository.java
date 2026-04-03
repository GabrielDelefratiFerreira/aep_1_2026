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
