package br.com.sicredi.assemblybe.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import lombok.Data;

@Data
public class SessionRequest implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;

	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)
	private Long agendaId;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)
	private Integer minutes;

}
