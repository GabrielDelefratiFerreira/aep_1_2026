package org.api.routes.solicitacao;

import org.api.core.JsonMapper;
import org.api.core.Enum.EnumStatus;
import org.api.core.Enum.EnumPrioridade;
import org.api.routes.historico.HistoricoStatusModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitacaoModel extends JsonMapper {

    private Long id;
    private String descricao;
    private String local;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime prazo;

    @JsonProperty("id_usuario")
    private Long id_usuario;

    @JsonProperty("id_categoria")
    private Long id_categoria;

    private EnumPrioridade prioridade;
    private EnumStatus status;
    private List<HistoricoStatusModel> historico;

    public SolicitacaoModel(
            Long id,
            String descricao,
            String local,
            LocalDateTime dataSolicitacao,
            LocalDateTime prazo,
            Long id_categoria,
            EnumPrioridade prioridade,
            EnumStatus status,
            List<HistoricoStatusModel> historico,
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
        this.historico = historico;
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
        this.historico = new ArrayList<>();
    }

    public SolicitacaoModel() {}

    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setLocal(String local) { this.local = local; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public void setPrazo(LocalDateTime prazo) { this.prazo = prazo; }
    public void setId_usuario(Long id_usuario) { this.id_usuario = id_usuario; }
    public void setId_categoria(Long id_categoria) { this.id_categoria = id_categoria; }
    public void setPrioridade(EnumPrioridade prioridade) { this.prioridade = prioridade; }
    public void setStatus(EnumStatus status) { this.status = status; }
    public void setHistorico(List<HistoricoStatusModel> historico) { this.historico = historico; }

    public Long getId() { return this.id; }
    public String getDescricao() { return this.descricao; }
    public String getLocal() { return this.local; }
    public LocalDateTime getDataSolicitacao() { return this.dataSolicitacao; }
    public LocalDateTime getPrazo() { return this.prazo; }
    public Long getUserId() { return this.id_usuario; }
    public Long getCategoryId() { return this.id_categoria; }
    public EnumPrioridade getPrioridade() { return this.prioridade; }
    public EnumStatus getStatus() { return this.status; }
    public List<HistoricoStatusModel> getHistorico() { return this.historico; }
}
