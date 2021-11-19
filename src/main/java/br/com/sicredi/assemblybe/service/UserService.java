package br.com.sicredi.assemblybe.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.assemblybe.constant.AssemblyConstant;
import br.com.sicredi.assemblybe.dto.UserValidateDTO;
import br.com.sicredi.assemblybe.enums.UserEnum;
import br.com.sicredi.assemblybe.exception.BusinessException;
import br.com.sicredi.assemblybe.integration.UserIntegration;
import br.com.sicredi.assemblybe.model.User;
import br.com.sicredi.assemblybe.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserIntegration userIntegration;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean validate(String cpf) throws BusinessException {
		
		UserValidateDTO userStatus = this.userIntegration.getUserStatus(cpf);
		
		if (userStatus == null) {
			throw new BusinessException(AssemblyConstant.MSG_USER_INVALID);
		}
		
		if (userStatus.getStatus().equalsIgnoreCase(UserEnum.ABLE_TO_VOTE.name())) {
			return true;
		} else if (userStatus.getStatus().equalsIgnoreCase(UserEnum.UNABLE_TO_VOTE.name())) {
			throw new BusinessException(AssemblyConstant.MSG_USER_UNABLE_TO_VOTE);
		} else {
			throw new BusinessException(AssemblyConstant.MSG_USER_UNKNOW);
		}		
	}
	
	public User getUserByTaxId(String taxId) throws BusinessException {
		
		 Optional<User> userOptional = this.userRepository.findByTaxId(taxId);	
		 
		 if (userOptional.isEmpty()) {
			validate(taxId);		
			
			User user = User.builder().taxId(taxId).dateCreated(LocalDateTime.now()).build();
			return this.userRepository.save(user);
		 }
		 
		return userOptional.get();
	}
}
