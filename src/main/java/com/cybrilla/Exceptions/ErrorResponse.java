package com.cybrilla.Exceptions;


import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@XmlRootElement(name = "error")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErrorResponse {
	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	// General error message about nature of error
	private String message;

	// Specific errors in API request processing
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	// Getter and setters
}