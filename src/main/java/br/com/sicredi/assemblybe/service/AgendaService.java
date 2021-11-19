package br.com.sicredi.assemblybe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.exception.NotFoundException;
import br.com.sicredi.assemblybe.model.Agenda;
import br.com.sicredi.assemblybe.repository.AgendaRepository;

@Service
public class AgendaService {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	public Agenda create(Agenda agenda) throws BusinessException {
		
		if (this.agendaRepository.findByName(agenda.getName()).isPresent()) {
			throw new BusinessException(AssemblyConstant.MSG_AGENDA_ALREADY_EXISTS);
		}
		
		agenda.setDateCreated(LocalDateTime.now());
		
		return this.agendaRepository.save(agenda);		
	}

	public Agenda getAgendaByid(Long id) throws NotFoundException {
		
		Optional<Agenda> agenda = this.agendaRepository.findById(id);
		
		if (!agenda.isPresent()) {
			throw new NotFoundException(String.format(AssemblyConstant.MSG_AGENDA_NOT_FOUND, id ));		
		}

		return agenda.get();	
		
	}

	public List<Agenda> getAgendas() {
		return this.agendaRepository.findAll();		
	}
}
