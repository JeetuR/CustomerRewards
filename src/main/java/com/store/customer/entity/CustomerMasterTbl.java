/**
 * 
 */
package com.store.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author jroddam
 *
 */

@Entity
@Table(name = "customer_master")
@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerMasterTbl extends BaseEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
	
    private String firstName;
    private String lastName;
    
    private String emailId;
    private Long  contactNumber;

    private String address;

    private boolean isInactive;
    
    
}
