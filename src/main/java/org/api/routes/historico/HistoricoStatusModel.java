package org.api.routes.historico;

import java.time.LocalDateTime;
import org.api.core.Enum.EnumStatus;

public class HistoricoStatusModel {
    private Long id;
    private LocalDateTime data;
    private String comentario;
    private EnumStatus status;

    public HistoricoStatusModel(Long id, LocalDateTime data, String comentario, EnumStatus status) {
        this.id = id;
        this.data = data;
        this.comentario = comentario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }
}
