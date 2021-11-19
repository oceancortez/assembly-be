package br.com.sicredi.assemblybe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.assemblybe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByTaxId(String taxId);

}
