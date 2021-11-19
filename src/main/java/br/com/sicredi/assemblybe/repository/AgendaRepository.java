package br.com.sicredi.assemblybe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	Optional<Agenda> findByName(String name);

	Optional<List<Agenda>> findByDateCountingIsNull();

}
