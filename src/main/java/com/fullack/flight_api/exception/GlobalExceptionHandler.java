package com.fullack.flight_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fullack.flight_api.http.ResponseMessage;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage<Map<String, Object>>> handleValidationException(
			MethodArgumentNotValidException ex) {
		Map<String, Object> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			Map<String, String> errorDetails = new HashMap<>();
			errorDetails.put("rejectedValue",
					fieldError.getRejectedValue() == null ? "null" :
					fieldError.getRejectedValue().toString());
			errorDetails.put("message", fieldError.getDefaultMessage());

			errors.put(fieldError.getField(), errorDetails);
		}

		ResponseMessage<Map<String, Object>> response = 
				ResponseMessage.error(400, "Validation Errors", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseMessage<String>> 
			handleResourceNotFoundException(ResourceNotFoundException ex,
			HttpServletRequest request) {

		ResponseMessage<String> response = ResponseMessage.error(404, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseMessage<String>> handleUnexpectedException(InvalidCredentialsException ex) {
		ResponseMessage<String> response = ResponseMessage.error(403,
				"Invalid Credentials: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage<String>> handleUnexpectedException(Exception ex) {
		ResponseMessage<String> response = ResponseMessage.error(500,
				"An unexpected error happened: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
