package org.api.routes.auth;

public class AuthModel {
  private String email;
  private String password;

  AuthModel() {}
  AuthModel(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }
}
