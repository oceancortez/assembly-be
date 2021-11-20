package br.com.sicredi.assemblybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.Vote;
import br.com.sicredi.assemblybe.model.VotePK;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VotePK> {
	
	@Query(value = " select v from Vote v where v.id.agendaId = :agendaId ")
	List<Vote> getVotesByAgendaId(Long agendaId);
}
