package com.pagos.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class CustomException extends RuntimeException {

	private static final long serialVersionUID = 3091941342926831397L;

	public CustomException(String message) {
		super(message);

	}



	
}
