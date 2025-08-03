package com.erdal.exeptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.erdal.exeptions.response.ExeptionResponse;

@ControllerAdvice
public class GlobleExeptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExeptionResponse> exeptionHandler(Exception ex, WebRequest req){ 
		
		ExeptionResponse exeptionResponse= new ExeptionResponse(ex.getMessage(),req.getDescription(false),LocalDateTime.now());
		
	return	ResponseEntity.ok(exeptionResponse);
		
	}

}
