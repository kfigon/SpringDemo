package com.example.demo.component;

import com.example.demo.entity.MyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MyException> handleConflict(Exception ex, ServletWebRequest request) {
        MyException exception = MyException.builder()
                .exception(ex.getClass().getCanonicalName())
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .path(request.getRequest().getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(exception);
    }
}
