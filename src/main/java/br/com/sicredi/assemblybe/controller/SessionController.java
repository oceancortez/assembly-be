package br.com.sicredi.assemblybe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.model.Session;
import br.com.sicredi.assemblybe.request.SessionRequest;
import br.com.sicredi.assemblybe.service.SessionService;

@RestController
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping(path = "session")
	public ResponseEntity<?> create(@Valid @RequestBody SessionRequest sessionRequest) throws BusinessException {	
		Session session = Session.builder().agendaId(sessionRequest.getAgendaId()).minutes(sessionRequest.getMinutes()).build();
		return new ResponseEntity<>(this.sessionService.create(session), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "sessions/open")
	public ResponseEntity<?> getSessionsOpen() throws NotFoundException, BusinessException {
		return ResponseEntity.ok(this.sessionService.getOpenSessions());
	}

}
