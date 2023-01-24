package com.store.customer.dao;


import java.util.Date;
import java.util.List;

/**
 * @author jroddam
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.customer.entity.CustomerMasterTbl;
import com.store.customer.entity.TransactionsTbl;

@Repository
public interface CustomerTransactionDAO extends JpaRepository<TransactionsTbl, Integer> {
    
	List<TransactionsTbl> findAllByCustomerIdAndCreatedOnGreaterThanEqual(CustomerMasterTbl customer, Date createdOn);
    
}