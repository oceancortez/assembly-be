package br.com.sicredi.assemblybe.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class VotePK implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;

	private Long sessionId;
	private Long agendaId;
	private Long userId;

}
