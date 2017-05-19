package com.jusfoun.exception;

import com.jusfoun.web.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RESTful 异常处理
 * Created by liutiyang on 2017/3/13.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(RestException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse();
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if (StringUtils.isNumeric(defaultMessage.replace("-", ""))) {
            error.setCode(Integer.parseInt(defaultMessage));
            error.setMessage(ExceptionMessage.getMessageByCode(Integer.parseInt(defaultMessage)));
        } else {
            error.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(BindException ex) {
        ErrorResponse error = new ErrorResponse();
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if (StringUtils.isNumeric(defaultMessage.replace("-", ""))) {
            error.setCode(Integer.parseInt(defaultMessage));
            error.setMessage(ExceptionMessage.getMessageByCode(Integer.parseInt(defaultMessage)));
        } else {
            error.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorResponse.builder().message(errorMsg).build();
    }
    */
}
