package com.hydroQ.HydroQApi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.HydroQApi.Repository.PowerOutageRepository;
import com.hydroQ.PowerOutages.BussinessLogic.PowerOutageLogic;
import com.hydroQ.PowerOutages.Entity.PowerOutage;

@Service
public class PowerOutageServiceImpl implements PowerOutageService {

	@Autowired
	private PowerOutageRepository powerRepo;

	@Override
	public PowerOutage createPowerOutage(PowerOutage powerOutage) {
		// TODO Auto-generated method stub
		
		

		String address = powerOutage.getAddress();
		List<PowerOutage> listPowerOutage = (List<PowerOutage>) powerRepo.findAll();
		
		
		
		//validation the requirement if the power Outage with give address id already exist or not
		//using custom business logic class PowerOutageLogic.checkAddressPresent
		Long isIdPresent = PowerOutageLogic.checkAddressPresent(powerOutage, listPowerOutage) ;

		
		//if powerOutage is present then just update the updated time and date value 
		if (isIdPresent != null) {

			
			PowerOutage exitingPowerOutage = powerRepo.findById(isIdPresent.longValue()).get();
			exitingPowerOutage.setUpdatedDate(powerOutage.getUpdatedDate());
			exitingPowerOutage.setUpdatedTime(powerOutage.getUpdatedTime());
			powerRepo.save(exitingPowerOutage);
			return exitingPowerOutage;

		} else {
			powerRepo.save(powerOutage);
			return powerOutage;
		}

	}

	@Override
	public void deletePowerOutage(Long id) {
		// TODO Auto-generated method stub
		powerRepo.deleteById(id);
	}

	@Override
	public Optional<PowerOutage> getPowerOutageById(Long id) {
		// TODO Auto-generated method stub
		return powerRepo.findById(id);
	}

	@Override
	public void updatePowerOutage(Long Id, PowerOutage powerOutageUpdated) {
		// TODO Auto-generated method stub

		Optional<PowerOutage> exitingEmployee = powerRepo.findById(Id);

		if (exitingEmployee.isPresent()) {
			
			//updating new values 
			PowerOutage poUpdate = exitingEmployee.get();
			poUpdate.setAddress(powerOutageUpdated.getAddress());
			poUpdate.setDescription(powerOutageUpdated.getDescription());
			poUpdate.setEmployees(powerOutageUpdated.getEmployees());
			poUpdate.setStartDate(powerOutageUpdated.getStartDate());
			poUpdate.setStartTime(powerOutageUpdated.getStartTime());
			poUpdate.setStatus(powerOutageUpdated.getStatus());
			poUpdate.setUpdatedDate(powerOutageUpdated.getUpdatedDate());
			poUpdate.setUpdatedTime(powerOutageUpdated.getUpdatedTime());
			
			powerRepo.save(poUpdate);

		}

	}

	@Override
	public Iterable<PowerOutage> getAllPowerOutages() {
		// TODO Auto-generated method stub
		return powerRepo.findAll();
	}

}
