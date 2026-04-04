package org.api.routes.token;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.api.core.ApiException;
import org.api.core.JsonMapper;

public class TokenService {
  private TokenRepository repo = new TokenRepository();
  private static final String AES_KEY = "1234567890123456";

  public TokenModel validateToken(String token) throws ApiException {
    String t = this.repo.findByToken(token);
    if (t == null)
      throw ApiException.unauthorized("Token not found!");
    if (!t.equals(token))
      throw ApiException.unauthorized("Received data does not match, access was prohibited!");

    TokenModel receivedToken = this.unwrap(token);
    TokenModel dbToken = this.unwrap(t);
    if (!receivedToken.getId().equals(dbToken.getId()))
      throw ApiException.unauthorized("Received data does not match, access was prohibited!");
    return dbToken;
  }

  public String generateToken(Long id) throws ApiException {
    try {
      long deadline = java.time.Instant.now().getEpochSecond() + 600;
      TokenModel obj = new TokenModel(repo.getNewId(), id, deadline);
      this.repo.save(obj);
      return encrypt(obj);
    } catch (Exception e) {
      throw ApiException.badRequest("Could not generate token: " + e);
    }
  }

  private TokenModel unwrap(String encryptedToken) throws ApiException {
    try {
      String json = decrypt(encryptedToken);
      TokenModel token = JsonMapper.fromJson(json, TokenModel.class);

      long actualTimestamp = java.time.Instant.now().getEpochSecond();
      if (token.getDeadline() <= actualTimestamp)
        throw ApiException.unauthorized("Token has expired!");

      return token;
    } catch (Exception e) {
      throw ApiException.unauthorized("Could not convert token: " + e);
    }
  }

  static TokenModel reverseHash(String encrypted) throws ApiException {
    try {
      String json = decrypt(encrypted);
      return JsonMapper.fromJson(json, TokenModel.class);
    } catch (Exception e) {
      throw ApiException.unauthorized("Failed to decrypt token!");
    }
  }

  static String encrypt(TokenModel obj) throws Exception {
    String json = JsonMapper.toJson(obj);
    SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return Base64.getEncoder().encodeToString(cipher.doFinal(json.getBytes(StandardCharsets.UTF_8)));
  }

  static String decrypt(String encrypted) throws Exception {
    SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)), StandardCharsets.UTF_8);
  }
}
