package com.store.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date createdOn;
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;

}
