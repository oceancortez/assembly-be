package br.com.sicredi.assemblybe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.request.VoteRequest;
import br.com.sicredi.assemblybe.service.VoteService;

@RestController
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping(path = "vote")
	public ResponseEntity<?> vote(@Valid @RequestBody VoteRequest voteRequest) throws BusinessException, NotFoundException {
		this.voteService.vote(voteRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
