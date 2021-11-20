package br.com.sicredi.assemblybe.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import lombok.Data;

@Data
public class AgendaRequest implements Serializable{
	
	private static final long serialVersionUID = 1761680612202087260L;

	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	private String name;	

}
