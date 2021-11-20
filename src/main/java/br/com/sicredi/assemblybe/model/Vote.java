package br.com.sicredi.assemblybe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table
public class Vote implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;
	
	@EmbeddedId
	private VotePK id;	
	private String voteStatus;	
	private LocalDateTime voteDate;	

}
