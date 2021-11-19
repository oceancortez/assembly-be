package br.com.sicredi.assemblybe.exception;

public class NotFoundException extends Exception {

	private String message;
	private static final long serialVersionUID = -4933533096473672184L;
	
	public NotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}	
}
