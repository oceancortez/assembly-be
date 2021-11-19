package br.com.sicredi.assemblybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.Vote;
import br.com.sicredi.assemblybe.model.VotePK;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VotePK> {

	
	
	@Query(value = " select vote_status as voteStatus, v.count as qtd from (select vote_status, count(*) as count from vote WHERE agenda_id = :agendaId group by vote_status) v group by v.count order by qtd "
			, nativeQuery = true)
	List<Object[]> getSummarizeByAgendaId(Long agendaId);
}
