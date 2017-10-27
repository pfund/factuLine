package com.thomaspfund.checkconsult.dto;

public class MonthResume {

	private int month;
	private int year;
	
	private Double consultationPrice;
	private Double materialPrice;
	private Double medicamentPrice;

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
	public Double getConsultationPrice() {
		return consultationPrice;
	}
	public void setConsultationPrice(Double consultationPrice) {
		this.consultationPrice = consultationPrice;
	}
	public Double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}
	public Double getMedicamentPrice() {
		return medicamentPrice;
	}
	public void setMedicamentPrice(Double medicamentPrice) {
		this.medicamentPrice = medicamentPrice;
	}
	
}
