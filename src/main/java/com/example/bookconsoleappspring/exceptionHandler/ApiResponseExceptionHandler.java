package com.example.bookconsoleappspring.exceptionHandler;

import com.example.bookconsoleappspring.dto.GetByIdException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiResponseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        String message = exception.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        GetByIdException getByIdException = new GetByIdException(
                message,
                status,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(getByIdException, status);
    }
}
