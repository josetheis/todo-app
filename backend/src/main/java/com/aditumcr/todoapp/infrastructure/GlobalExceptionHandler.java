package com.aditumcr.todoapp.infrastructure;

import com.aditumcr.todoapp.domain.errors.DomainException;
import com.aditumcr.todoapp.domain.errors.TodoAlreadyExistsException;
import com.aditumcr.todoapp.domain.errors.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {TodoNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFound(TodoNotFoundException ex) {
        return ErrorDTO.toResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ErrorDTO> handleDomainException(DomainException ex) {
        return ErrorDTO.toResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST, new Date());
    }

    @ExceptionHandler(value = {TodoAlreadyExistsException.class})
    public ResponseEntity<ErrorDTO> handleAlreadyExists(TodoAlreadyExistsException ex) {
        return ErrorDTO.toResponseEntity(ex.getMessage(), HttpStatus.CONFLICT, new Date());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorDTO> handleUnexpectedError(RuntimeException ex) {
        return ErrorDTO.toResponseEntity(
                "Un error inesperado ocurrio, intente mas tarde",
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date()
        );
    }

    private record ErrorDTO(
            String message,
            HttpStatus httpStatus,
            Date timestamp
    ) {
        static ResponseEntity<ErrorDTO> toResponseEntity(String message, HttpStatus httpStatus, Date timestamp) {
            ErrorDTO error = new ErrorDTO(message, httpStatus, timestamp);
            return new ResponseEntity<>(error, error.httpStatus);
        }
    }
}
