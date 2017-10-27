package com.thomaspfund.checkconsult.entity.administration;

import com.thomaspfund.checkconsult.entity.MongoEntity;

public class OperationHospital implements MongoEntity {
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
