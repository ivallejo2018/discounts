package com.globant.discounts.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.persistence.Type;

public class DiscountHandler implements FileHandler {

	private Resource resource;
	
	private static AtomicInteger sequence = new AtomicInteger(0);
	
	public DiscountHandler() {
		
	}
	
	public DiscountHandler(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public List<Discount> read(Integer companyId) throws DiscountException {
		List<Discount> discountsAvailable = new ArrayList<>();
		List<Discount> results = new ArrayList<>();
		
		try (BufferedReader file = new BufferedReader(new FileReader(resource.getFile()))) {
			String line = file.readLine();
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				Discount discountRetrieved = 
						new Discount(Integer.valueOf(token.nextToken()), 
								Integer.valueOf(token.nextToken()), 
								Double.valueOf(token.nextToken()),
								Type.valueOf(token.nextToken()));	
				discountsAvailable.add(discountRetrieved);
				line = file.readLine();
			}
			results = 
					discountsAvailable.stream().filter(d -> d.getCompanyId() == companyId).collect(Collectors.toList());
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
				discount.setId(sequence.incrementAndGet());
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
	public Discount overwrite(Integer companyId, Integer discountId, Discount discount) throws DiscountException {
		
		try (RandomAccessFile file = new RandomAccessFile(resource.getFile(), "rw")) {
			
			String line = file.readLine();
			int length = line.length() + 1;
			
			while(line != null) {
				
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				Discount discountRetrieved = 
						new Discount(Integer.valueOf(token.nextToken()), 
								Integer.valueOf(token.nextToken()), 
								Double.valueOf(token.nextToken()),
								Type.valueOf(token.nextToken()));	
				if(discountRetrieved.getCompanyId() == companyId &&
						discountRetrieved.getId() == discountId) {
					file.seek(length - line.length() - 1);
					file.writeBytes(discount.toString());	
					return discountRetrieved;
				}
				
				line = file.readLine();
				length += line.length() + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public void erase(Integer companyId, Integer discountId) throws DiscountException {
		
		try (RandomAccessFile file = new RandomAccessFile(resource.getFile(), "rw")) {

			String line = file.readLine();
			int length = line.length() + 1;
			
			while(line != null) {
				
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				Discount discountRetrieved = 
						new Discount(Integer.valueOf(token.nextToken()), 
								Integer.valueOf(token.nextToken()), 
								Double.valueOf(token.nextToken()),
								Type.valueOf(token.nextToken()));	
				
				if(discountRetrieved.getCompanyId() == companyId &&
						discountRetrieved.getId() == discountId) {
					file.seek(length-3);
					file.writeBytes(DEL_CHAR);
					break;
				}
				
				line = file.readLine();
				length += line.length() + 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
	}

}
