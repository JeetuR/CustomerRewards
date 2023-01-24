/**
 * 
 */
package com.store.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.customer.entity.CustomerMasterTbl;

/**
 * @author jroddam
 *
 */
@Repository
public interface CustomerDAO extends JpaRepository<CustomerMasterTbl, Integer> {

}
