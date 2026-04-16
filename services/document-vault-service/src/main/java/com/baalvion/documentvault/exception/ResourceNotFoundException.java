package com.baalvion.documentvault.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8518353139658357091L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
	}
}