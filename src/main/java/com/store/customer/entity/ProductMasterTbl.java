package com.store.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_master")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ProductMasterTbl extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String productCode;
	private String productName;
	private Double price;

	public ProductMasterTbl(Integer id) {
		this.id = id;
	}
}
