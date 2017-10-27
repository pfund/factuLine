package com.thomaspfund.checkconsult.dto;


public class MonthOperationResume {
	private int month;
	private int year;
	
	private Double amount;
	private Double assistantOneAmount;
	private Double assistantTwoAmount;
	private Integer numberAssistantPaid;
	private Integer numberAssistedOperations;
	
	private Integer numberOperations;
	private Integer numberPaymentsRecieved;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAssistantOneAmount() {
		return assistantOneAmount;
	}
	public void setAssistantOneAmount(Double assistantOneAmount) {
		this.assistantOneAmount = assistantOneAmount;
	}
	public Double getAssistantTwoAmount() {
		return assistantTwoAmount;
	}
	public void setAssistantTwoAmount(Double assistantTwoAmount) {
		this.assistantTwoAmount = assistantTwoAmount;
	}
	public Integer getNumberAssistantPaid() {
		return numberAssistantPaid;
	}
	public void setNumberAssistantPaid(Integer numberAssistantPaid) {
		this.numberAssistantPaid = numberAssistantPaid;
	}
	public Integer getNumberAssistedOperations() {
		return numberAssistedOperations;
	}
	public void setNumberAssistedOperations(Integer numberAssistedOperations) {
		this.numberAssistedOperations = numberAssistedOperations;
	}
	public Integer getNumberOperations() {
		return numberOperations;
	}
	public void setNumberOperations(Integer numberOperations) {
		this.numberOperations = numberOperations;
	}
	public Integer getNumberPaymentsRecieved() {
		return numberPaymentsRecieved;
	}
	public void setNumberPaymentsRecieved(Integer numberPaymentsRecieved) {
		this.numberPaymentsRecieved = numberPaymentsRecieved;
	} 
	

	
}
