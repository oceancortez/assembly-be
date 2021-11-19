package br.com.sicredi.assemblybe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import lombok.Data;

@Data
@Entity
@Table
public class Session implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	private Long agendaId;
	
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;
	
	@Transient
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)
	private Integer minutes;

}
