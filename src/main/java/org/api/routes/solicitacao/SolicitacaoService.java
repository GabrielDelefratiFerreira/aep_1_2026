package org.api.routes.solicitacao;

import org.api.core.ApiException;
import org.api.routes.historico.HistoricoService;
import org.api.routes.historico.HistoricoStatusModel;
import java.util.List;

public class SolicitacaoService {
    private SolicitacaoRepository repositorySolicitacao = new SolicitacaoRepository();
    private HistoricoService historicoService = new HistoricoService();

    public SolicitacaoService() {
    }

    public void create(SolicitacaoModel solicitacao) {
        SolicitacaoModel solicitacaoRes = repositorySolicitacao.save(solicitacao);
        this.historicoService.create(solicitacaoRes);
    }

    public List<SolicitacaoModel> findAll() {
        return repositorySolicitacao.findAll();
    }

    public void update(Long id, SolicitacaoModel solicitacao) {
        solicitacao.setId(id);
        this.historicoService.create(solicitacao);
        this.repositorySolicitacao.update(solicitacao);
    }

    public void revoke(Long id) {
        this.repositorySolicitacao.revoke(id);
    }

    public List<HistoricoStatusModel> getHistory(Long id) {
        return this.historicoService.findAllBySolicitacao(id);
    }

    public SolicitacaoModel byId(Long id) throws ApiException {
        // TODO: se o banco for implementado fora do java esse for nao pode mais
        // existir, precisa ser implementada na query do sql dentro do repo
        List<SolicitacaoModel> solicitacoes = this.repositorySolicitacao.findAll();
        for (SolicitacaoModel item : solicitacoes) {
            if (item.getId().equals(id))
                return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
