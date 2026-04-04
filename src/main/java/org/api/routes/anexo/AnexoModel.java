package org.api.routes.anexo;

import org.api.core.JsonMapper;

public class AnexoModel extends JsonMapper {
  private Long id;
  private Long solicitacaoId;
  private String anexo;

  public AnexoModel() {
  }

  public AnexoModel(Long id, Long solicitacaoId, String anexo) {
    this.anexo = anexo;
    this.id = id;
    this.solicitacaoId = solicitacaoId;
  }

  public String getAnexo() {
    return this.anexo;
  }

  public Long getId() {
    return this.id;
  }

  public Long getSolicitacaoId() {
    return this.solicitacaoId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSolicitacaoId(Long id) {
    this.solicitacaoId = id;
  }

  public void setAnexo(String anexo) {
    this.anexo = anexo;
  }

}
