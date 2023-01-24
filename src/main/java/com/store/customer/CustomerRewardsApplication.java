/**
 * 
 */
package com.store.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jroddam
 *
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.store.customer.*"})
public class CustomerRewardsApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Initiating Rewards Application.....");
		SpringApplication.run(CustomerRewardsApplication.class, args);

	}
	
	

}
