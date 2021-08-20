package com.globits.adapter.opcassist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class CompanyToJSON {
  public static void main(String[] args) throws JsonProcessingException {
     SimpleFilterProvider filterProvider = new SimpleFilterProvider();
     filterProvider.addFilter("ceoFilter",
             SimpleBeanPropertyFilter.serializeAllExcept("empProfile"));
     
     filterProvider.addFilter("addressFilter",
             SimpleBeanPropertyFilter.filterOutAllExcept("state", "country"));
     
     ObjectMapper mapper = new ObjectMapper();
     mapper.setFilterProvider(filterProvider);
     
     Employee emp = new Employee("Krishna", 45, "Manager");
     Address address = new Address("Noida", "UP", "India");
     Company company = new Company("XYZ", emp, address);
     String jsonData = mapper.writerWithDefaultPrettyPrinter()
		 .writeValueAsString(company);
     System.out.println(jsonData);
  }
} 