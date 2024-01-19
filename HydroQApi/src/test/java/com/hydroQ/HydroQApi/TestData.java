package com.hydroQ.HydroQApi;

import java.util.ArrayList;
import java.util.List;

import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.PowerOutages.Entity.PowerOutage;


//to create test data 
public class TestData {
	
	 public static PowerOutage createSamplePowerOutage() {
	        PowerOutage powerOutage = new PowerOutage();
	        
	        // Set sample data
	        powerOutage.setId(2L);
	        powerOutage.setEmployees(createSampleEmployees());
	        powerOutage.setStartDate("10/11");
	        powerOutage.setStartTime("12:00");
	        powerOutage.setUpdatedDate("10/11");
	        powerOutage.setUpdatedTime("12:05");
	        powerOutage.setAddress("H9L023");
	        powerOutage.setStatus("In Progress");
	        powerOutage.setDescription("Customer X: NotWorking – the client heard a strong noise.");

	        return powerOutage;
	    }

	    private static List<Employee> createSampleEmployees() {
	        // Create and return a list of sample employees
	        List<Employee> employees = new ArrayList<>();

	        Employee meet = new Employee();
			meet.setId(2L);
			meet.setFirstName("meet");
			meet.setLastName("patel");
			meet.setPhoneNumber("5555555555");
			meet.setEmergencyContactNumber("Emergency Contact");
			meet.setEmail("meet@meet.com");
	        employees.add(meet);

	        Employee patrick = new Employee();
	        patrick.setFirstName("Patrick");
	        patrick.setLastName("oza");
	        patrick.setPhoneNumber("5555555555");
	        patrick.setEmergencyContactNumber("Emergency Contact");
	        patrick.setEmail("patrick@patel.com");
	        employees.add(patrick);

	        return employees;
	    }
	    
	

}
