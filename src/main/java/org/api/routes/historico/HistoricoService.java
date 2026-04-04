package org.api.routes.historico;

import org.api.core.ApiException;
import org.api.routes.historico.HistoricoService;
import org.api.routes.solicitacao.SolicitacaoModel;
import java.time.LocalDateTime;
import java.util.List;

public class HistoricoService {
    private HistoricoRepository repositoryHistorico = new HistoricoRepository();

    public HistoricoService() {
    }

    public void create(SolicitacaoModel solicitacao) {
        HistoricoStatusModel hist = new HistoricoStatusModel(
                null,
                LocalDateTime.now(),
                solicitacao.getDescricao(),
                solicitacao.getStatus(),
                solicitacao.getId());
        repositoryHistorico.save(hist);
    }

    public List<HistoricoStatusModel> findAllBySolicitacao(Long solicitacaoId) {
        return repositoryHistorico.findAll(solicitacaoId);
    }

    public void revoke(Long id) {
        this.repositoryHistorico.revoke(id);
    }

    public HistoricoStatusModel byId(Long id) throws ApiException {
        return this.repositoryHistorico.findById(id);
    }
}
