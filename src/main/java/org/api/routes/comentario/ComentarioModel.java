package org.api.routes.comentario;

import java.time.LocalDateTime;
import org.api.core.JsonMapper;

public class ComentarioModel extends JsonMapper {
  private Long id;
  private Long userId;
  private Long solicitacaoId;
  private LocalDateTime createdAt;
  private String message;

  public ComentarioModel() {
  }

  public ComentarioModel(Long id, Long userId, Long solicitacaoId, LocalDateTime createdAt, String message) {
    this.id = id;
    this.userId = userId;
    this.solicitacaoId = solicitacaoId;
    this.createdAt = createdAt;
    this.message = message;
  }

  public void setId(Long param) {
    this.id = param;
  }

  public void setUserId(Long param) {
    this.userId = param;
  }

  public void setSolicitacaoId(Long param) {
    this.solicitacaoId = param;
  }

  public void setCreatedAt(LocalDateTime param) {
    this.createdAt = param;
  }

  public void setMessage(String param) {
    this.message = param;
  }

  public Long getId() {
    return this.id;
  }

  public Long getUserId() {
    return this.userId;
  }

  public Long getSolicitacaoId() {
    return this.solicitacaoId;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getMessage() {
    return this.message;
  }

}
