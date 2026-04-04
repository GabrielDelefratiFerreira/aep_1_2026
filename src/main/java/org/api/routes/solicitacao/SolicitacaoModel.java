package org.api.routes.solicitacao;

import org.api.core.JsonMapper;
import org.api.core.Enum.EnumStatus;
import org.api.core.Enum.EnumPrioridade;
import java.time.LocalDateTime;

public class SolicitacaoModel extends JsonMapper {
    private Long id;
    private String descricao;
    private String local;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime prazo;
    private Long id_usuario;
    private Long id_categoria;
    private EnumPrioridade prioridade;
    private EnumStatus status;

    public SolicitacaoModel() {}
    public SolicitacaoModel(
            Long id,
            String descricao,
            String local,
            LocalDateTime dataSolicitacao,
            LocalDateTime prazo,
            Long id_categoria,
            EnumPrioridade prioridade,
            EnumStatus status,
            Long id_usuario) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.dataSolicitacao = dataSolicitacao;
        this.prazo = prazo;
        this.id_usuario = id_usuario;
        this.id_categoria = id_categoria;
        this.prioridade = prioridade;
        this.status = status;
    }
    public SolicitacaoModel(
            Long newId,
            String descricao,
            String local,
            LocalDateTime prazo,
            Long id_categoria,
            EnumPrioridade prioridade,
            EnumStatus status,
            Long id_usuario) {
        this.id = newId;
        this.descricao = descricao;
        this.local = local;
        this.dataSolicitacao = LocalDateTime.now();
        this.prazo = prazo;
        this.id_usuario = id_usuario;
        this.id_categoria = id_categoria;
        this.prioridade = prioridade;
        this.status = status;
    }


    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setLocal(String local) { this.local = local; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public void setPrazo(LocalDateTime prazo) { this.prazo = prazo; }
    public void setId_usuario(Long id_usuario) { this.id_usuario = id_usuario; }
    public void setId_categoria(Long id_categoria) { this.id_categoria = id_categoria; }
    public void setPrioridade(EnumPrioridade prioridade) { this.prioridade = prioridade; }
    public void setStatus(EnumStatus status) { this.status = status; }

    public Long getId() { return this.id; }
    public String getDescricao() { return this.descricao; }
    public String getLocal() { return this.local; }
    public LocalDateTime getDataSolicitacao() { return this.dataSolicitacao; }
    public LocalDateTime getPrazo() { return this.prazo; }
    public Long getUserId() { return this.id_usuario; }
    public Long getCategoryId() { return this.id_categoria; }
    public EnumPrioridade getPrioridade() { return this.prioridade; }
    public EnumStatus getStatus() { return this.status; }
}
