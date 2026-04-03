package org.api.routes.prioridade;

public class PrioridadeModel {
    private Long id;
    private String nivel;

    public PrioridadeModel(Long id, String nivel) {
        this.id = id;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
