package workshop.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import workshop.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandler {

	private ResponseEntity<StandardError> handlerBuilder(String error, HttpStatus status, Exception e,
			HttpServletRequest request) {

		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(HttpServletRequest request,
			ObjectNotFoundException objectNotFoundException) {
		String error = "Not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handlerBuilder(error, status, objectNotFoundException, request);
	}
}
