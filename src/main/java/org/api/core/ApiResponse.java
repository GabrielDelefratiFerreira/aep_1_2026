package org.api.core;

public class ApiResponse {
  int status;
  String message;

  private ApiResponse(String message, int status) {
    this.status = status;
    this.message = message;
    System.out.println("[LOG]: " + message);
  }

  public String getMessage() {
    return this.message;
  }

  public int getStatusCode() {
    return this.status;
  }

  public static ApiResponse ok(String message) {
    return new ApiResponse(message, 200);
  }

  public static ApiResponse created(String message) {
    return new ApiResponse(message, 201);
  }

  public static ApiResponse accepted(String message) {
    return new ApiResponse(message, 202);
  }

  public static ApiResponse noContent(String message) {
    return new ApiResponse(message, 204);
  }

  public static ApiResponse found(String message) {
    return new ApiResponse(message, 302);
  }

}
