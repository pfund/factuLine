package com.thomaspfund.checkconsult.entity;

import java.util.Date;

public class Assistance implements MongoEntity {

	private String id;
	
	private Date dateAssistance;
	
	private String operator;
	
	private String firstName;
	
	private String lastName;
	
	private String operationType;
	
	private String operationHospital;
	
	private Double amount;
	
	private Date paidDate;
	
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateAssistance() {
		return dateAssistance;
	}

	public void setDateAssistance(Date dateAssistance) {
		this.dateAssistance = dateAssistance;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationHospital() {
		return operationHospital;
	}

	public void setOperationHospital(String operationHospital) {
		this.operationHospital = operationHospital;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
