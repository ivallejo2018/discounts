package com.globant.discounts.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Utility class to read/write to/from a file
 * The file is being used as a repository of the data of the discount service
 * 
 * @author isaac.vallejo
 *
 */
@Component
public class FileUtil {

	@Value("file:authentication.txt")
	private Resource resourceAuthentication;
	
	public static final String SEPARATOR = "|";
	
	/**
	 * Writes data to the repository file
	 * 
	 * @param elements				The data that will be written to the repository file
	 * @throws DiscountException	In case of a failure throws a customized exception
	 */
	public void write(List<?> elements) throws DiscountException {
		
		try (FileWriter file = new FileWriter(resourceAuthentication.getFile(), true)){
			
			Iterator<?> iterator = elements.iterator();
			while(iterator.hasNext()) {
				Object element = iterator.next();
				file.write(element.toString());
			}
			file.write("\n");
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
	}
	
	/**
	 * Reads the data from the repository file
	 *  
	 * @param column	The column which the associated data will be found
	 * @param value		The value searched
	 * @return			A list with the results of the information retrieved
	 * @throws DiscountException	In case of a failure throws a customized exception
	 */
	public List<List<Object>> read(Integer column, Object value) throws DiscountException {
		List<List<Object>> elements = new ArrayList<>();
		
		try (BufferedReader file = new BufferedReader(new FileReader(resourceAuthentication.getFile()))) {
			String line = file.readLine();
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, SEPARATOR);
				List<Object> temporaryStore = new ArrayList<>();
				while(token.hasMoreTokens()) {
					temporaryStore.add(token.nextToken());
				}	
				
				//If the searched data has matched with the repository data then it will be stored on a collection
				if(!temporaryStore.isEmpty() && temporaryStore.get(column).equals(value))
					elements.add(temporaryStore);
				line = file.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscountException(e.getMessage());
		}
		
		return elements;
	}
}
