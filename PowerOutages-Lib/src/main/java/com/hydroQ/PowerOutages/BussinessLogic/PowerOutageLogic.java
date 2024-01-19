package com.hydroQ.PowerOutages.BussinessLogic;

import java.util.List;

import com.hydroQ.PowerOutages.Entity.PowerOutage;

//business logic class to validate that 
//is there any powerOutage exist with the same address 
//if yes then return id of that power outage 

public class PowerOutageLogic {

	public static Long checkAddressPresent(PowerOutage powerOutage, List<PowerOutage> listPowerOutage) {

		String address = powerOutage.getAddress();
		boolean isPresentAddress = false;
		Long id = null;

		for (PowerOutage p : listPowerOutage) {
			if (p.getAddress().equalsIgnoreCase(address)) {
				isPresentAddress = true;
				id = p.getId();
			}
		}

		return id;

	}

}
