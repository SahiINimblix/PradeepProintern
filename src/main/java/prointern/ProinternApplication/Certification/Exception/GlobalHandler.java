package prointern.ProinternApplication.Certification.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalHandler {

	@ExceptionHandler(ResourseNotFoundException.class)

	ResponseEntity<ErrorMessage> errror (ResourseNotFoundException ex , WebRequest request){

		ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND.value() , ex.getMessage() , LocalDateTime.now());
		return new ResponseEntity<ErrorMessage>(err , HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String , Object>> error(MethodArgumentNotValidException ex , WebRequest request){

		Map<String , Object> error = new HashMap<>();

		error.put("HttpStatus :", HttpStatus.NOT_FOUND);
		error.put("TimeStramp :" , LocalDateTime.now());
		error.put("error :", "Not Found");
		error.put("Path :", request.getDescription(false));

		ex.getBindingResult().getFieldErrors().forEach(err -> error.put(err.getField() , err.getDefaultMessage()));

		return new ResponseEntity<>( error , HttpStatus.BAD_REQUEST );	

	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest request) {
        ErrorMessage er = new ErrorMessage( HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(),LocalDateTime.now());
 
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
