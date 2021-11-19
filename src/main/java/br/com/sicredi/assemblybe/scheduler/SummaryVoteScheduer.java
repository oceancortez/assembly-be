package br.com.sicredi.assemblybe.scheduler;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sicredi.assemblybe.service.SummaryVoteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SummaryVoteScheduer {
	
    @Autowired
    private SummaryVoteService service;  

     @Scheduled(cron="${SCHEDULED_CRON}")
     public void doIt() {
    	log.info(":: SummaryVoteScheduer.doIt() Start :: "+ LocalDate.now() + " :: "+ LocalTime.now());        
        this.service.summarize();        
        log.info(":: SummaryVoteScheduer.doIt() End :: "+ LocalDateTime.now());
    }
}
