package com.globant.discounts.persistence;

import com.globant.discounts.util.FileHandler;
import com.globant.discounts.util.FileUtil;

public class Discount {

	private int id;
	private int companyId;
	private double percentage;
	private Type type;
	
	public Discount() {
		super();
	}
	
	public Discount(int id, int companyId, double percentage, Type type) {
		this.id = id;
		this.companyId = companyId;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.companyId);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.percentage);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.type);
		sb.append(FileUtil.SEPARATOR);
		sb.append(FileHandler.CREATE_CHAR);
		sb.append(FileUtil.SEPARATOR);
		
		return sb.toString();
	}
}
