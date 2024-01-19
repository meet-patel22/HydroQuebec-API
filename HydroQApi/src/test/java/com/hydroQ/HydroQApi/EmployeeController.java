package com.hydroQ.HydroQApi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroQ.Employee.Entity.Employee;
import com.hydroQ.HydroQApi.Service.EmployeeService;
import com.hydroQ.HydroQApi.Service.PowerOutageService;
import com.hydroQ.PowerOutages.Entity.PowerOutage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController {

	@MockBean
	EmployeeService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	void save() throws Exception {

		// Setup
		Employee meet = new Employee();
		meet.setId(2L);
		meet.setFirstName("meet");
		meet.setLastName("patel");
		meet.setPhoneNumber("5555555555");
		meet.setEmergencyContactNumber("Emergency Contact");
		meet.setEmail("meet@meet.com");

		// Mocking the service save method
		Mockito.when(service.createEmployee(Mockito.any(Employee.class))).thenReturn(meet);

		// Execution and Assert
		mockMvc.perform(post("/employee/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(meet))).andDo(print()).andExpect(status().isCreated());

	}

	@Test
	void findById() throws Exception {

		// Setup
		Employee meet = new Employee();
		meet.setId(2L);
		meet.setFirstName("meet");
		meet.setLastName("patel");
		meet.setPhoneNumber("5555555555");
		meet.setEmergencyContactNumber("Emergency Contact");
		meet.setEmail("meet@meet.com");

		Mockito.when(service.getEmployeeById(Mockito.anyLong())).thenReturn(Optional.of(meet));

		// Execute
		mockMvc.perform(get("/employee/?id=2")).andDo(print())
				// Assert
				.andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("meet@meet.com"));
	}

	@Test
	void deleteTest() throws Exception {

		// Setup
		Employee meet = new Employee();
		meet.setId(2L);
		meet.setFirstName("meet");
		meet.setLastName("patel");
		meet.setPhoneNumber("5555555555");
		meet.setEmergencyContactNumber("Emergency Contact");
		meet.setEmail("meet@meet.com");
		// execute
		Mockito.doNothing().when(service).deleteEmployee(Mockito.anyLong());

		// Execution
		mockMvc.perform(delete("/employee/?id=2")).andDo(print())
				// Assert
				.andExpect(status().isAccepted());

	}

}
