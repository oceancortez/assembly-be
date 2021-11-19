package br.com.sicredi.assemblybe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

	@Query(value = " select * from omc.session where id = :sessionId and agenda_id = :agendaId and now() between opening_date and closing_date "
			, nativeQuery = true)
	Optional<Session> getActiveSession(Long sessionId, Long agendaId);

	@Query(" select s from Session s where s.closingDate > now() ")
	Optional<List<Session>> getOpenSessions();

	Optional<Session> findByAgendaId(Long agendaId);

}
