package br.com.sicredi.assemblybe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.model.Session;
import br.com.sicredi.assemblybe.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	public Session create(Session session) throws BusinessException {
				
		if (this.sessionRepository.findByAgendaId(session.getAgendaId()).isPresent()) {
			throw new BusinessException(AssemblyConstant.MSG_SESSION_ALREADY_EXISTS);
		}
		
		LocalDateTime now = LocalDateTime.now();
		session.setOpeningDate(now);
		
		Integer minutes = session.getMinutes();
		if (minutes == null || minutes == 0) {
			minutes = 1;			
		}
		
		session.setClosingDate(now.plusMinutes(minutes));
		
		return this.sessionRepository.save(session);
		
	}
	
	public Session getActiveSession(Long sessionId, Long agendaId) throws NotFoundException {
		Optional<Session> activeSession = this.sessionRepository.getActiveSession(sessionId, agendaId);
		return activeSession.orElseThrow(() -> new NotFoundException(AssemblyConstant.MSG_SESSION_WAS_CLOSED));
	}

	public List<Session> getOpenSessions() throws BusinessException {
		
		Optional<List<Session>> openSessions = this.sessionRepository.getOpenSessions();
		
		if (openSessions.isEmpty() || CollectionUtils.isEmpty(openSessions.get())) {
			throw new BusinessException(AssemblyConstant.MSG_SESSION_NOT_FOUND);
		}
		
		return openSessions.get();		
	}

}
