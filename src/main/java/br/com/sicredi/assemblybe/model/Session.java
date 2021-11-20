package br.com.sicredi.assemblybe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table
public class Session implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long agendaId;	
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;
	
	@Transient
	private Integer minutes;
}
