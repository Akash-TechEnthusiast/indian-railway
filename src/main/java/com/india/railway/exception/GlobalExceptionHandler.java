package com.india.railway.exception;




import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
	@ExceptionHandler(value = NoSuchEmployeeExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(NoSuchEmployeeExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
    
	
	@ExceptionHandler(value = EmployeeAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(EmployeeAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	 
}

