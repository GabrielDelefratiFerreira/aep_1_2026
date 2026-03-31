package org.example.Service;

import org.example.Enum.EnumStatus;
import org.example.Model.solicitacao;
import org.example.Repository.repositorySolicitacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class solicitacaoService {

    private repositorySolicitacao repositorySolicitacao;

    public solicitacaoService(repositorySolicitacao repositorySolicitacao) {
        this.repositorySolicitacao = repositorySolicitacao;
    }

    public void criarSolicitacao(solicitacao solicitacao){
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        solicitacao.setHistorico(new ArrayList<>());
        solicitacao.setStatus(EnumStatus.TRIAGEM);
        repositorySolicitacao.salvarSolicitacao(solicitacao);
    }

    public List<solicitacao> findAll(){
        return repositorySolicitacao.findAll();
    }


}