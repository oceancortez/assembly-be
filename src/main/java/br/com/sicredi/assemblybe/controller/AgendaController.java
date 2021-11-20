package br.com.sicredi.assemblybe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.model.Agenda;
import br.com.sicredi.assemblybe.request.AgendaRequest;
import br.com.sicredi.assemblybe.service.AgendaService;

@RestController
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping(path = "agendas")
	public ResponseEntity<?> getAgendas() {		
		return ResponseEntity.ok(this.agendaService.getAgendas());
	}
	
	@GetMapping(path = "agenda/{id}")
	public ResponseEntity<?> getAgendaById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return ResponseEntity.ok(this.agendaService.getAgendaByid(id));
	}
	
	@PostMapping(path = "agenda")
	public ResponseEntity<?> create(@Valid @RequestBody AgendaRequest agendaRequest) throws BusinessException {
		return new ResponseEntity<>(this.agendaService.create(Agenda.builder().name(agendaRequest.getName()).build()), HttpStatus.CREATED);
	}

}
