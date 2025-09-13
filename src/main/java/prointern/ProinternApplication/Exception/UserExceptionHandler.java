package prointern.ProinternApplication.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundExce(Exception exception, WebRequest request) {
		ErrorMessage e =new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(), request.getDescription(false));
//		return new ResponseEntity<ErrorMessage>(e, HttpStatus.CONFLICT);//here the status will be written in the right side
		return new ResponseEntity<ErrorMessage>(e, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DetailsNotFoundException.class)
	public ResponseEntity<ErrorMessage> detailNotFoundExce(Exception exception, WebRequest request) {
		ErrorMessage e =new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(), request.getDescription(false));
//		return new ResponseEntity<ErrorMessage>(e, HttpStatus.CONFLICT);//here the status will be written in the right side
		return new ResponseEntity<ErrorMessage>(e, HttpStatus.NOT_FOUND);
	}
}
