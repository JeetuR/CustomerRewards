/**
 * 
 */
package com.store.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jroddam
 *
 */
@Component
public class RewardsConfig {
	
	@Value("${rewards.AMOUNT_100}")
	public Integer AMOUNT_100 = 0;
	
	@Value("${rewards.AMOUNT_50}")
	public Integer AMOUNT_50 = 0;
	
	@Value("${rewards.POINTS_50_TO_100}")
	public Integer POINTS_50_TO_100 = 0;
	
	@Value("${rewards.POINTS_100_ABOVE}")
	public Integer POINTS_100_ABOVE = 0;
	
	@Value("${rewards.transaction.months}")
	public Integer TRANSACTIONS_PERIOD = 0;
	
}
