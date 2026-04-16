package com.baalvion.documentversioning.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

	private int status;
	private String message;
	private Map<String, String> errors;
	private LocalDateTime timestamp;

	public ErrorResponse() {
	}

	public ErrorResponse(int status, String message, Map<String, String> errors, LocalDateTime timestamp) {
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.timestamp = timestamp;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int status;
		private String message;
		private Map<String, String> errors;
		private LocalDateTime timestamp;

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder errors(Map<String, String> errors) {
			this.errors = errors;
			return this;
		}

		public Builder timestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ErrorResponse build() {
			return new ErrorResponse(status, message, errors, timestamp);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}