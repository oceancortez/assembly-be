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
		
		if (agendas.isEmpty() || CollectionUtils.isEmpty(agendas.get())) {
			return;
		}
		
		agendas.get().forEach(agenda -> {
			
			Optional<Session> session = this.sessionRepository.findByAgendaId(agenda.getId());			
			if (session.isEmpty()) {
				return;
			}			
		
			Optional<Session> activeSession = 
					this.sessionRepository.getActiveSession(session.get().getId(), agenda.getId());
		
			if (activeSession.isPresent()) {
				return;
			}	

			List<Object[]> summary = this.voteRepository.getSummarizeByAgendaId(agenda.getId());			
			Optional<Agenda> agendaOptional = this.agendaRepository.findById(agenda.getId());
			
			long qtdVoteTotal = 0;
			
			agendaOptional.get().setNVoteTotal(0L);
			agendaOptional.get().setNVoteTotal(0L);
			
			for (Object[] obj : summary) {
				
				String voteStatus = String.valueOf(obj[0]);
				long qtd = Long.parseLong(String.valueOf(obj[1]));
				
				if (voteStatus.equalsIgnoreCase(AssemblyConstant.YES)) {
					agendaOptional.get().setSVoteTotal(qtd);
				} else {
					agendaOptional.get().setNVoteTotal(qtd);
				}				
				qtdVoteTotal += qtd;
			}				
			agendaOptional.get().setQtdVoteTotal(qtdVoteTotal);
			agendaOptional.get().setDateCounting(LocalDateTime.now());
			
			this.agendaRepository.saveAndFlush(agendaOptional.get());					
		});
	}	
}
