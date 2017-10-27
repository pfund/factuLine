package com.thomaspfund.checkconsult.dto;


public class MonthAssistanceResume {
	private int month;
	private int year;
	
	private Double amount;
	
	private Integer numberPaidAssistances;
	private Integer numberAssistances;
	
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
	public Integer getNumberPaidAssistances() {
		return numberPaidAssistances;
	}
	public void setNumberPaidAssistances(Integer numberPaidAssistances) {
		this.numberPaidAssistances = numberPaidAssistances;
	}
	public Integer getNumberAssistances() {
		return numberAssistances;
	}
	public void setNumberAssistances(Integer numberAssistances) {
		this.numberAssistances = numberAssistances;
	}
	
	
}
