package org.api.routes.token;

import java.util.ArrayList;
import java.util.List;
import org.api.core.ApiException;

public class TokenRepository {
  private List<String> banco = new ArrayList<>();
  private Long idIndex = 0L;

  public void save(TokenModel token) throws ApiException {
    if (token.getId() == null)
      token.setId(getNewId());
    try {
      banco.add(TokenService.encrypt(token));
    } catch (Exception e) {
      throw ApiException.internal("Could not encrypt token: " + e);
    }
  }

  public void update(TokenModel token) throws ApiException {
    for (int i = 0; i < banco.size(); i++) {
      if ((TokenService.reverseHash(banco.get(i))).getId().equals(token.getId())) {
        banco.set(i, banco.get(i));
        return;
      }
    }
  }

  public String findByToken(String token) throws ApiException {
    for (String t : banco) {
      if (t.equals(token))
        return t;
    }
    throw ApiException.notFound("Token was not found");
  }

  public void revoke(Long id) throws ApiException {
    banco.removeIf((s) -> {
      try {
        return TokenService.reverseHash(s).getId().equals(id);
      } catch (ApiException e) {
        return false;
      }
    });
  }

  public Long getNewId() {
    return ++this.idIndex;
  }
}
