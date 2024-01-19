package com.hydroQ.HydroQApi.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.HydroQApi.Repository.EmployeeRepository;
import com.hydroQ.HydroQApi.Repository.PowerOutageRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee);
	}

	@Override
	public void updateEmployee(Long id, Employee employee) {
		// TODO Auto-generated method stub

		Optional<Employee> exitingEmployee = empRepo.findById(id);

		
		//updating new values 
		if (exitingEmployee.isPresent()) {
			Employee empUpdate = exitingEmployee.get();
			empUpdate.setEmail(employee.getEmail());
			empUpdate.setEmergencyContactNumber(employee.getEmergencyContactNumber());
			empUpdate.setFirstName(employee.getFirstName());
			empUpdate.setLastName(employee.getLastName());
			empUpdate.setPhoneNumber(employee.getPhoneNumber());

			empRepo.save(empUpdate);

		}

	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = empRepo.findById(id);
		emp.ifPresent(value -> empRepo.delete(value));

	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id);
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
