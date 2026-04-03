package org.api.routes.usuario;

import org.api.core.JsonMapper;

public class UsuarioModel extends JsonMapper {
    private Long id;
    private String nome;
    private String email;

    public UsuarioModel(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
