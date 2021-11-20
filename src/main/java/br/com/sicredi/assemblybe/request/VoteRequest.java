package br.com.sicredi.assemblybe.request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import lombok.Data;

@Data
public class VoteRequest {
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)
	private String userTaxId;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)	
	private Long sessionId;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Min(value = 1, message = AssemblyConstant.MSG_SESSION_MINUTE)
	private Long agendaId;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Pattern(regexp = "S|N", message= AssemblyConstant.MSG_VOTE_STATUS_YES_OR_NO)
	private String voteStatus;

}
