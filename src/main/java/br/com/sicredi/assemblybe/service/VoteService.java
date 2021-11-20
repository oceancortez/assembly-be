package br.com.sicredi.assemblybe.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.model.User;
import br.com.sicredi.assemblybe.model.Vote;
import br.com.sicredi.assemblybe.model.VotePK;
import br.com.sicredi.assemblybe.repository.VoteRepository;
import br.com.sicredi.assemblybe.request.VoteRequest;

@Service
public class VoteService {
	
	@Autowired 
	private VoteRepository voteRepository;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private UserService userService;
	
	public void vote(VoteRequest voteRequest) throws BusinessException, NotFoundException {		
		
		User user = this.userService.getUserByTaxId(voteRequest.getUserTaxId());
			
		this.sessionService.getActiveSession(voteRequest.getSessionId(), voteRequest.getAgendaId());
			
		
		VotePK votePK = VotePK.builder().sessionId(voteRequest.getSessionId()).agendaId(voteRequest.getAgendaId())
				.userId(user.getId()).build();
		
		
		Optional<Vote> voteOptional = this.voteRepository.findById(votePK);
		
		if (voteOptional.isPresent()) {
			throw new BusinessException(AssemblyConstant.MSG_VOTE_USER_HAD_VOTED);
		}
		
		Vote vote = Vote.builder()
				.id(votePK)
				.voteDate(LocalDateTime.now())
				.voteStatus(voteRequest.getVoteStatus())
				.build();					
			
		this.voteRepository.save(vote);
	}
	
}
