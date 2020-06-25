package com.sapient.football.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestApiError {

  private HttpStatus status;
  private String message;

}
