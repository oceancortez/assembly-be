package br.com.sicredi.assemblybe.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

	@Query(value = " select * from omc.session where id = :sessionId and agenda_id = :agendaId and :dateNow between opening_date and closing_date "
			, nativeQuery = true)
	Optional<Session> getActiveSession(Long sessionId, Long agendaId, LocalDateTime dateNow);

	@Query(" select s from Session s where s.closingDate > :dateNow ")
	Optional<List<Session>> getOpenSessions(LocalDateTime dateNow);

	Optional<Session> findByAgendaId(Long agendaId);

}
