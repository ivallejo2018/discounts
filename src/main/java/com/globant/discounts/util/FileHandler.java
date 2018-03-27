package com.globant.discounts.util;

import java.util.List;

import org.springframework.core.io.Resource;

public interface FileHandler<T> {

	public static final String SEPARATOR = "|";
	
	public static final String NEW_LINE = "\n";
	
	public List<T> read(T object) throws DiscountException;
	
	public void write(List<T> objects) throws DiscountException;
	
	public void overwrite(T src, T dest) throws DiscountException;
}
