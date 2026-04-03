package org.api.routes.solicitacao;

import org.api.core.ApiException;
import java.util.List;

public class SolicitacaoService {
    private SolicitacaoRepository repositorySolicitacao;

    public SolicitacaoService() {
        this.repositorySolicitacao = new SolicitacaoRepository();
    }

    public void create(SolicitacaoModel solicitacao) {
        repositorySolicitacao.save(solicitacao);
    }

    public List<SolicitacaoModel> findAll() {
        return repositorySolicitacao.findAll();
    }

    public void update(Long id, SolicitacaoModel solicitacao) {

    }

    public void revoke(Long id) {
        this.repositorySolicitacao.revoke(id);
    }

    public SolicitacaoModel byId(Long id) throws ApiException {
        // TODO: se o banco for implementado fora do java esse for nao pode mais
        // existir, precisa ser implementada na query do sql dentro do repo
        List<SolicitacaoModel> solicitacoes = this.repositorySolicitacao.findAll();
        for (SolicitacaoModel item : solicitacoes) {
            if (item.getId().equals(id)) return item;
        }
        throw ApiException.notFound("Solicitação não encontrada!");
    }
}
