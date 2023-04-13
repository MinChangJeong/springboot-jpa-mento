package tuk.mentor.global.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tuk.mentor.global.s3.FileStorageException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> fileStorage(FileStorageException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ErrorResponse> servlet(ServletException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> io(IOException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exception(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}
