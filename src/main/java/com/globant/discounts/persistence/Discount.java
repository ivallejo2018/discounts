package com.globant.discounts.persistence;

public class Discount {

	private int id;
	private double percentage;
	private Type type;
	
	public Discount(int id, double percentage, Type type) {
		this.id = id;
		this.percentage = percentage;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
