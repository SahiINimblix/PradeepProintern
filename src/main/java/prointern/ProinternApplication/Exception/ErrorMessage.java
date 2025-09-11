package prointern.ProinternApplication.Exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
	private int StatusCode;
	private Date timestamp;
	private String message;
	private String description;

	public ErrorMessage(int StatusCode, Date timestamp, String message, String description) {
		// TODO Auto-generated constructor stub
		this.StatusCode = StatusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

	public int getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(int statusCode) {
		StatusCode = statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
