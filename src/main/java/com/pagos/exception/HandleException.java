package com.pagos.exception;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pagos.responses.ErrorResponse;

@RestControllerAdvice
public class HandleException {

	@ExceptionHandler({ DuplicateDataException.class })
	public ResponseEntity<ErrorResponse> handlerDuplicateDataException(DuplicateDataException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder().timestamp(new Date())
				.details(List.of(exception.getMessage())).error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ PairDayException.class })
	public ResponseEntity<ErrorResponse> handlerPairDayException(PairDayException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().timestamp(new Date())
				.details(List.of(exception.getMessage())).error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ OverflowValueException.class })
	public ResponseEntity<ErrorResponse> handlerOverflowValueException(OverflowValueException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().timestamp(new Date())
				.details(List.of(exception.getMessage())).error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ DateTimeParseException.class })
	public ResponseEntity<ErrorResponse> handlerDateTimeParseException(DateTimeParseException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.builder().timestamp(new Date())
						.details(List.of("Formato de fecha invalido, Formato valido dd/MM/uuuu"))
						.error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.builder().timestamp(new Date())
						.details(exception.getBindingResult().getAllErrors().stream()
								.map(ObjectError::getDefaultMessage).collect(Collectors.toList()))
						.error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<ErrorResponse> handlerEmptyResultDataAccessException(
			EmptyResultDataAccessException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().timestamp(new Date())
				.details(List.of("El Elemento no existe")).error(exception.getClass().getSimpleName()).build());
	}

	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<ErrorResponse> handlerNoSuchElementException(NoSuchElementException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().timestamp(new Date())
				.details(List.of("Elemento no encontrado")).error(exception.getClass().getSimpleName()).build());
	}

}
