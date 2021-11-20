package br.com.sicredi.assemblybe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import br.com.sicredi.assemblybe.model.Agenda;
import br.com.sicredi.assemblybe.model.Session;
import br.com.sicredi.assemblybe.model.Vote;
import br.com.sicredi.assemblybe.repository.AgendaRepository;
import br.com.sicredi.assemblybe.repository.SessionRepository;
import br.com.sicredi.assemblybe.repository.VoteRepository;

@Service
public class SummaryVoteService {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired 
	private VoteRepository voteRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	public void summarize() {		
		
		Optional<List<Agenda>> agendas = this.agendaRepository.findByDateCountingIsNull();
		
		if (!agendas.isPresent() || CollectionUtils.isEmpty(agendas.get())) {
			return;
		}
		
		agendas.get().forEach(agenda -> {
			
			Optional<Session> session = this.sessionRepository.findByAgendaId(agenda.getId());			
			if (!session.isPresent()) {
				return;
			}			
		
			Optional<Session> activeSession = 
					this.sessionRepository.getActiveSession(session.get().getId(), agenda.getId(), LocalDateTime.now());
		
			if (activeSession.isPresent()) {
				return;
			}	

			List<Vote> votes = this.voteRepository.getVotesByAgendaId(agenda.getId());						
			
			Long countYes = votes.stream()
					  .filter(f -> f.getVoteStatus().equalsIgnoreCase(AssemblyConstant.YES)).count();
			
			Long countNo = votes.stream()
					  .filter(f -> f.getVoteStatus().equalsIgnoreCase(AssemblyConstant.NO)).count();

			Optional<Agenda> agendaOptional = this.agendaRepository.findById(agenda.getId());
			
			agendaOptional.get().setSVoteTotal(countYes);
			agendaOptional.get().setNVoteTotal(countNo);
							
			agendaOptional.get().setQtdVoteTotal(countYes + countNo);
			agendaOptional.get().setDateCounting(LocalDateTime.now());
			
			this.agendaRepository.saveAndFlush(agendaOptional.get());	
		
		});
	}	
}
