/**
 * 
 */
package com.store.customer.service;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * @author jroddam
 *
 */
@Builder
@Data
@Jacksonized
public class RewardsInfo {
	private String customerRewards;
}
