package com.hydroQ.HydroQApi.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.PowerOutages.Entity.PowerOutage;


@Service
public interface PowerOutageService {
	//to craete 
	PowerOutage createPowerOutage(PowerOutage powerOutage ) ;
	
	//to update 
	void updatePowerOutage(Long Id, PowerOutage powerOutageUpdated ) ;
	
	//to delete
	void deletePowerOutage(Long id) ;
	
	//to find by id 
	Optional<PowerOutage> getPowerOutageById(Long id) ;
	
	//to find all
	Iterable<PowerOutage> getAllPowerOutages(); 
	

}
