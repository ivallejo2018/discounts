package com.globant.discounts.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	@Value("classpath:authentication.txt")
	private Resource resourceAuthentication;
	
	public void write(List<Object> elements) {
		
		try (FileWriter file = new FileWriter(resourceAuthentication.getFile())){
			while(elements.iterator().hasNext()) {
				Object element = elements.iterator().next();
				file.write(String.valueOf(element), 5, String.valueOf(element).length());
			}
			file.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<List<Object>> read(Integer column, Object value) {
		List<List<Object>> elements = new ArrayList<>();
		
		try (BufferedReader file = new BufferedReader(new FileReader(resourceAuthentication.getFile()))) {
			String line = file.readLine();
			while(line != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				List<Object> temporaryStore = new ArrayList<>();
				while(token.hasMoreTokens()) {
					temporaryStore.add(token.nextToken());
				}	
				
				if(!temporaryStore.isEmpty() && temporaryStore.get(column).equals(value))
					elements.add(temporaryStore);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return elements;
	}
}
