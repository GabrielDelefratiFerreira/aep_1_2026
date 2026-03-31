package org.example.Model;

import org.example.Enum.EnumStatus;

import java.time.LocalDateTime;
import java.util.List;

public class solicitacao {

    private Long id;
    private String descricao;
    private String local;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime prazo;

    private usuario usuario;
    private categoria categoria;
    private prioridade prioridade;
    private EnumStatus status;

    private List<historicoStatus> historico;

    public solicitacao(Long id, String descricao, String local) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrioridade(prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public List<historicoStatus> getHistorico() {
        return historico;
    }

    public void setHistorico(List<historicoStatus> historico) {
        this.historico = historico;
    }

    public void setDataSolicitacao(LocalDateTime data){
        this.dataSolicitacao = data;
    }
}