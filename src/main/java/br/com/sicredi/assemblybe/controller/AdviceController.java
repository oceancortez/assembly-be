package br.com.sicredi.assemblybe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;


@ControllerAdvice
public class AdviceController {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> exceptionHandler(final NotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> exceptionHandler(final BusinessException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
    	
    	StringBuilder message = new StringBuilder();    	
        BindingResult results = ex.getBindingResult();
        results.getFieldErrors().forEach(e -> message.append("\n Campo '"+ e.getField()+"' " + e.getDefaultMessage()));
        
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }

}