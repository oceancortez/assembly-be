package br.com.sicredi.assemblybe.constant;

public class AssemblyConstant {
	
	private AssemblyConstant() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static final String MSG_NOT_NULL = "não pode ser nulo!";
	public static final String MSG_VOTE_STATUS_YES_OR_NO = "deve ser S ou N ";
	public static final String MSG_SESSION_MINUTE = "O valor deve ser maior igual 1";
	
	public static final String YES = "S";
	public static final String NO = "N";
	public static final String MSG_AGENDA_ALREADY_EXISTS = "Já existe uma pauta com esse nome, favor cadastrar com outro nome";
	public static final String MSG_AGENDA_NOT_FOUND = "Agenda id:%s não cadastrada";
	public static final String MSG_SESSION_ALREADY_EXISTS = "Já existe uma sessão cadastrar com essa pauta, favor criar uma nova pauta";
	public static final String MSG_SESSION_WAS_CLOSED = " Essa sessão não está mais aberta ";
	public static final String MSG_SESSION_NOT_FOUND = "Não existem sessões abertas no momento ";
	public static final String MSG_USER_INVALID = "Usuário Invalido";
	public static final String MSG_USER_UNABLE_TO_VOTE = "Usuário não habilitado para votar";
	public static final String MSG_USER_UNKNOW = "Usuário com Status Desconhecido";
	public static final String MSG_VOTE_USER_HAD_VOTED = "Usuário Já votou";

}
