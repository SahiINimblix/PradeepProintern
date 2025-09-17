package prointern.ProinternApplication.Exception;

//Save/Update/Delete Failure Exception
public class OperationFailedException extends RuntimeException {
 public OperationFailedException(String message) {
     super(message);
 }
}