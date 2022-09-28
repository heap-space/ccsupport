package com.ccsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** 
 * Spring boot application initialisation point
 * Enables JPA repository since application use JPA for accessing database
 * Entity scan configuration given to scan the entities
 * @author  heap-space
 * @version 1.0 
 * @since   2020-03-20 
 */

@SpringBootApplication
@EnableJpaRepositories("com.ccsupport.repository")
@EntityScan("com.ccsupport.entity")
public class TicketApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class,args);
		
	}

}
