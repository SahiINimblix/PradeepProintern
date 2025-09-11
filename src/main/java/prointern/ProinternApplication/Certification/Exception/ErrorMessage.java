package prointern.ProinternApplication.Certification.Exception;

import java.time.LocalDateTime;

public class ErrorMessage {
	
	private int HttpStatus;
	private String description;
	private LocalDateTime timestarmp;
	public ErrorMessage(int httpStatus, String description, LocalDateTime timestarmp) {
		super();
		HttpStatus = httpStatus;
		this.description = description;
		this.timestarmp = timestarmp;
	}
	public int getHttpStatus() {
		return HttpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		HttpStatus = httpStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTimestarmp() {
		return timestarmp;
	}
	public void setTimestarmp(LocalDateTime timestarmp) {
		this.timestarmp = timestarmp;
	}
	@Override
	public String toString() {
		return "ErrorMessage [HttpStatus=" + HttpStatus + ", description=" + description + ", timestarmp=" + timestarmp
				+ "]";
	}
	
	
	

}
