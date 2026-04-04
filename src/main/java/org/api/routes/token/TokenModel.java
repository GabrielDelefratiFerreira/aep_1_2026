package org.api.routes.token;

import org.api.core.JsonMapper;

public class TokenModel extends JsonMapper {
  private Long id;
  private Long userId;
  private long deadline;

  public TokenModel() {}
  public TokenModel(Long id, Long userId, long deadline) {
    this.id = id;
    this.userId = userId;
    this.deadline = deadline;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public Long getUserId() {
    return this.userId;
  }

  public long getDeadline() {
    return this.deadline;
  }
}
