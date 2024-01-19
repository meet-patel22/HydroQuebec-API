package com.hydroQ.HydroQApi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hydroQ.Employee.Entity.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>  {

}
