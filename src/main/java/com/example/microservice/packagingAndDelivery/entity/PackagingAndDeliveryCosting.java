package com.example.microservice.packagingAndDelivery.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicecharges")
public class PackagingAndDeliveryCosting {
	@Id
	private String id;
	private String costType;
	private String price;
	
	public PackagingAndDeliveryCosting() {
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public PackagingAndDeliveryCosting(String id , String costType, String price){
		this.id = id;
		this.costType = costType;
		this.price = price;
	}
	

}
