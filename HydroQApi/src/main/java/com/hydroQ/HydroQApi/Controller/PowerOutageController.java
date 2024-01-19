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


import com.hydroQ.HydroQApi.Service.EmployeeService;
import com.hydroQ.HydroQApi.Service.PowerOutageService;
import com.hydroQ.PowerOutages.Entity.PowerOutage;



@RestController
@RequestMapping("/powerOutage")
public class PowerOutageController {

	
	@Autowired(required = false)
	private PowerOutageService poService;

	@PostMapping("/")
	public ResponseEntity<PowerOutage> save(@RequestBody PowerOutage powerOutage) {

		PowerOutage savedPowerOutage= poService.createPowerOutage(powerOutage);
		
		return savedPowerOutage != null ? new ResponseEntity<>(savedPowerOutage, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/")
	public ResponseEntity<PowerOutage> delete(@RequestParam Long id) {
		Optional<PowerOutage> deletedPowerOutage= poService.getPowerOutageById(id);

		if (deletedPowerOutage.isPresent()) {
			poService.deletePowerOutage(id);
			return new ResponseEntity<>( HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

	}

	@GetMapping("/")
	public ResponseEntity<PowerOutage> findById(@RequestParam Long id) {
		Optional<PowerOutage> savedPowerOutage= poService.getPowerOutageById(id);

		return savedPowerOutage.map(

				// if yes: send it, with a success code
				value -> new ResponseEntity<>(value, HttpStatus.FOUND))

				// otherwise: send it an error code
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PowerOutage>> findAll1() {
		List<PowerOutage> savedPowerOutages= (List<PowerOutage>) poService.getAllPowerOutages();

		if (!savedPowerOutages.isEmpty()) {
			return new ResponseEntity<>(savedPowerOutages, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<PowerOutage> update(@RequestParam Long id, @RequestBody PowerOutage updatedPowerOutage) {
		// Check if the Employee with the given ID exists
		Optional<PowerOutage> powerOutageToUpdate = poService.getPowerOutageById(id);

		if (powerOutageToUpdate.isPresent()) {
			// Update the Employee using the service implementation method
			poService.updatePowerOutage(id, updatedPowerOutage);

			// Return the updated Employee with a success code
			return new ResponseEntity<>(updatedPowerOutage, HttpStatus.OK);
		}

		// If the Employee with the given ID doesn't exist, return an error code
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
