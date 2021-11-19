package br.com.sicredi.assemblybe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private String name;	
	private LocalDateTime dateCreated;
	private Long sVoteTotal;
	private Long nVoteTotal;
	private Long qtdVoteTotal;
	private LocalDateTime dateCounting;

}
