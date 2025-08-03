package com.erdal.exeptions.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class ExeptionResponse {
	
	private String message;
	
	private String error;
	
	private LocalDateTime timeStamp;
	

}
