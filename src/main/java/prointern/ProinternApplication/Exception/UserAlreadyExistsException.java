package prointern.ProinternApplication.Exception;

//Duplicate Email Exception
public class UserAlreadyExistsException extends RuntimeException {
 public UserAlreadyExistsException(String message) {
     super(message);
 }
}
