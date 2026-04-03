package org.api.core;

import java.io.IOException;

public class ApiException extends IOException {
  private final int statusCode;

  public ApiException(String message, int statusCode) {
    super("[ERROR]: " + message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public int getBytesLenMessage() {
    return this.getMessage().getBytes().length;
  }

  public static ApiException notFound(String message) {
    return new ApiException(message, 404);
  }

  public static ApiException badRequest(String message) {
    return new ApiException(message, 400);
  }

  public static ApiException internal(String message) {
    return new ApiException(message, 500);
  }
}
