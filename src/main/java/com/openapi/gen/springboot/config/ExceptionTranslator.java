package com.openapi.gen.springboot.config;

import com.openapi.gen.springboot.dto.Error;
import com.openapi.gen.springboot.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final Map<String, List<String>> result = e.getBindingResult().getAllErrors()
                .stream()
                .map(it -> (FieldError) it)
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );
        return ResponseEntity.ok(getError(result.toString(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFoundException(final UserNotFoundException e) {
        return ResponseEntity.ok(getError(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }


    private Error getError(String message, Integer code) {
        Error error = new Error();
        error.setMessage(message);
        error.setCode(code);
        return error;
    }
}
