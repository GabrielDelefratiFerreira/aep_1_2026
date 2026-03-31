package org.example.Repository;

import org.example.Model.solicitacao;

import java.util.ArrayList;
import java.util.List;

public class repositorySolicitacao {
    private List<solicitacao> banco = new ArrayList<>();

    public void salvarSolicitacao(solicitacao solicitacao){
        banco.add(solicitacao);
    }

    public List<solicitacao> findAll() {
        return banco;
    }
}