/**
 * 
 */
package com.store.customer.rewards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.store.customer.CustomerRewardsApplication;
import com.store.customer.exception.ErrorMessages;
import com.store.customer.service.RewardsInfo;

/**
 * @author jroddam
 *
 */
@SpringBootTest(classes = CustomerRewardsApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ErrorMessages errorMsgs;
    
    @LocalServerPort
    private int port;

    @Test
    public void validCustomerWithRewards() throws Exception {
        
    	Integer customer = 301;

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/" + customer, RewardsInfo.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

        RewardsInfo rewardsInfo = (RewardsInfo) responseEntity.getBody(); 
        assertTrue(Double.parseDouble(rewardsInfo.getCustomerRewards()) == 367.28);   
        
    }

    @Test
    public void nonExistantCustomer() throws Exception {

    	Integer customer = 501;

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/"+ customer, String.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertTrue("Given Customer ID Not Found".equalsIgnoreCase((String)responseEntity.getBody()));
    }

    @Test
    public void invalidCustomerID() throws Exception {

    	String customer = "XYZ";

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/"+ customer, String.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertTrue("Given Customer ID Not Found".equalsIgnoreCase((String)responseEntity.getBody()));
    }

    @Test
    public void validCustomerWithoutAnyTransactions() throws Exception {
        
    	Integer customer = 201;

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/" + customer, String.class);
        assertEquals(400, responseEntity.getStatusCodeValue());

        assertTrue("No Eligible Transactions Found For Customer".equalsIgnoreCase((String)responseEntity.getBody()));
        
    } 

    @Test
    public void validCustomerWithoutAnyRewards() throws Exception {
        
    	Integer customer = 401;

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/" + customer, RewardsInfo.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

        RewardsInfo rewardsInfo = (RewardsInfo) responseEntity.getBody(); 
        assertTrue(Double.parseDouble(rewardsInfo.getCustomerRewards()) == 0.0);   
        
    } 
    
    
    /*
     * Border case.
     * 
     * One transaction beyond allowed transaction period ( should not be considered ). 
     * Amount in one month less than qualified month.
     * Amount in another month > than qualified month.
     */
    @Test
    public void validCustomerWithRewardsByMonth() throws Exception {
        
    	Integer customer = 101;

        ResponseEntity<?> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/customer/getRewardsPoints/" + customer, RewardsInfo.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

        RewardsInfo rewardsInfo = (RewardsInfo) responseEntity.getBody(); 
        assertTrue(Double.parseDouble(rewardsInfo.getCustomerRewards()) == 181.46);   
        
    }
    
    
    
    
    
}
