package com.globant.discounts.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.persistence.Type;

public class DiscountHandler implements FileHandler<Discount> {

	private Resource resource;
	
	public DiscountHandler() {

	}
	
	public DiscountHandler(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public List<Discount> read(Discount discount) throws DiscountException {
		List<Discount> discountsAvailable = new ArrayList<>();
		List<Discount> results = new ArrayList<>();
		
		try (BufferedReader file = new BufferedReader(new FileReader(resource.getFile()))) {
			String line = file.readLine();
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				Discount discountRetrieved = 
						new Discount(0, Integer.valueOf(token.nextToken()), 
								Double.valueOf(token.nextToken()),
								Type.valueOf(token.nextToken()));	
				discountsAvailable.add(discountRetrieved);
			}
			results = 
					discountsAvailable.stream().filter(d -> d.getCompanyId() == discount.getCompanyId() || 
					d.getType().equals(discount.getType()) || 
					d.getPercentage() == discount.getPercentage()).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
		return results;
	}

	@Override
	public void write(List<Discount> discounts) throws DiscountException {
		
		try (FileWriter file = new FileWriter(resource.getFile(), true)) {
			
			Iterator<Discount> iterator = discounts.iterator();
			while(iterator.hasNext()) {
				Discount discount = iterator.next();
				file.write(discount.toString());
				file.write(NEW_LINE);
			}
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
	}

	@Override
	public void overwrite(Discount src, Discount dest) throws DiscountException {
		
		try (RandomAccessFile file = new RandomAccessFile(resource.getFile(), "rw")) {
			
			String line = file.readLine();
			long bytesRead = 0;
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				Discount discountRetrieved = 
						new Discount(0, Integer.valueOf(token.nextToken()), 
								Double.valueOf(token.nextToken()),
								Type.valueOf(token.nextToken()));	
				bytesRead += line.length();
				if(discountRetrieved.getCompanyId() == src.getCompanyId() &&
						discountRetrieved.getType().equals(src.getType())) {
					file.seek(bytesRead - line.length());
					file.writeBytes(NEW_LINE);
					file.writeBytes(dest.getCompanyId() + SEPARATOR +
							dest.getPercentage() + SEPARATOR +
							dest.getType());	
					file.writeBytes(NEW_LINE);
					break;
				}
				line = file.readLine();
			} 
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
	}

}
