package com.teammental.common.web.controller;

import com.teammental.common.bll.dto.MessageDto;
import com.teammental.common.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleNotFoundException(NotFoundException ex) {
    LOGGER.debug(ex.getMessage());
  }
}
