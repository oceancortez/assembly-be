package br.com.sicredi.assemblybe.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import lombok.Data;

@Data
public class VoteDTO {
	
	private String userTaxId;
	private Long sessionId;
	private Long agendaId;
	
	@NotNull(message = AssemblyConstant.MSG_NOT_NULL)
	@Pattern(regexp = "S|N", message= AssemblyConstant.MSG_VOTE_STATUS_YES_OR_NO)
	private String voteStatus;

}
