package br.com.sicredi.assemblybe.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.assemblybe.dto.UserValidateDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserIntegration {
	
	@Value("${URL_VALIDATE_USER}")
	private String URL_VALIDATE_USER;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public UserValidateDTO getUserStatus(String cpf) {
        
        try {
        	
        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);
        	
        	HttpEntity<?> request = new HttpEntity<>(headers);
        	
        	ResponseEntity<UserValidateDTO> response = 
        			this.restTemplate.exchange(URL_VALIDATE_USER.concat(cpf), HttpMethod.GET, request, UserValidateDTO.class);
            
        	return response.getBody();
        	
        } catch (HttpClientErrorException e) {
        	return null;
        } catch (Exception e) {
        	log.error("ERROR - inside method getUserStatus message:", e);
        	return null;
		}
	}
}
