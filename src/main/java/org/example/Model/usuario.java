package org.example.Model;

public class usuario {
    private Long id;
    private String nome;
    private String emaio;

    public usuario(Long id, String nome, String emaio) {
        this.id = id;
        this.nome = nome;
        this.emaio = emaio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmaio() {
        return emaio;
    }

    public void setEmaio(String emaio) {
        this.emaio = emaio;
    }
}