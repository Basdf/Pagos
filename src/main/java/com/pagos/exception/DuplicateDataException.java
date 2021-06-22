package com.pagos.exception;


public class DuplicateDataException extends CustomException {
	private static final long serialVersionUID = 3678909767879589076L;

	public DuplicateDataException(String message) {
		super(message);
	}

}
