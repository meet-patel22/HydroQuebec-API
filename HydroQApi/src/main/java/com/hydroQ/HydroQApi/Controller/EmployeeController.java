package com.hydroQ.HydroQApi.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.HydroQApi.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired(required = false)
	private EmployeeService empService;

	@PostMapping("/")
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {

		Employee savedEmployee = empService.createEmployee(employee);
		
		return savedEmployee != null ? new ResponseEntity<>(savedEmployee, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/")
	public ResponseEntity<Employee> delete(@RequestParam Long id) {
		Optional<Employee> deletedEmployee = empService.getEmployeeById(id);

		if (deletedEmployee.isPresent()) {
			empService.deleteEmployee(id);
			return new ResponseEntity<>( HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

	}

	@GetMapping("/")
	public ResponseEntity<Employee> findById(@RequestParam Long id) {
		Optional<Employee> savedEmployee = empService.getEmployeeById(id);

		return savedEmployee.map(

				// if yes: send it, with a success code
				value -> new ResponseEntity<>(value, HttpStatus.FOUND))

				// otherwise: send it an error code
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> findAll1() {
		List<Employee> savedEmployees = (List<Employee>) empService.getAllEmployees();

		if (!savedEmployees.isEmpty()) {
			return new ResponseEntity<>(savedEmployees, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<Employee> update(@RequestParam Long id, @RequestBody Employee updatedEmployee) {
		// Check if the Employee with the given ID exists
		Optional<Employee> employeeToUpdateOptional = empService.getEmployeeById(id);

		if (employeeToUpdateOptional.isPresent()) {
			// Update the Employee using the service implementation method
			empService.updateEmployee(id, updatedEmployee);

			// Return the updated Employee with a success code
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		}

		// If the Employee with the given ID doesn't exist, return an error code
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

