package com.hydroQ.HydroQApi.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydroQ.Employee.Entity.Employee;

@Service
public interface EmployeeService {
	
	//to create 
	Employee createEmployee(Employee employee ) ;
	
	//to update 
    void updateEmployee(Long id ,Employee employee ) ;
    
    //to delete
	void deleteEmployee(Long id) ;
	
	//to find by id
	Optional<Employee> getEmployeeById(Long id) ;
	
	
	//to find all 
	Iterable<Employee> getAllEmployees(); 
	

}
