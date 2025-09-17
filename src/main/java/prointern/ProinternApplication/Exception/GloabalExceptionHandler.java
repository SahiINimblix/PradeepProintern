package prointern.ProinternApplication.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GloabalExceptionHandler {

    // Handle Wrong URL (404  Page not found)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Page not found");
        response.put("status", 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle Duplicate Email (409 Conflict)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmail(UserAlreadyExistsException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage()); // e.g. "Email already registered"
        response.put("status", 409);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle Save/Update/Delete Failures (406 Not Acceptable)
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Map<String, Object>> handleOperationFailed(OperationFailedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage()); // e.g. "Unable to save"
        response.put("status", 406);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    // Handle All Other Exceptions (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Something went wrong. Please try again.");
        response.put("status", 500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //Handle Details not found  while searching through ID/Email...
    @ExceptionHandler(DetailsNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(DetailsNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("status", 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
 // Handle Invalid ID format
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidId(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Invalid ID format");
        response.put("status", 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
