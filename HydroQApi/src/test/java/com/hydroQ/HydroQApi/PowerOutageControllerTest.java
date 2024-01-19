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
import com.hydroQ.HydroQApi.Service.PowerOutageService;
import com.hydroQ.PowerOutages.Entity.PowerOutage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PowerOutageControllerTest {

	@MockBean
	PowerOutageService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	void save() throws Exception {

		// Setup
		PowerOutage p = TestData.createSamplePowerOutage();

		// Mocking the service save method
		Mockito.when(service.createPowerOutage(Mockito.any(PowerOutage.class))).thenReturn(p);

		// Execution and Assert
		mockMvc.perform(post("/powerOutage/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(p))).andDo(print()).andExpect(status().isCreated());

	}

	@Test
	void findById() throws Exception {
		// Setup
		// Setup
		PowerOutage p = TestData.createSamplePowerOutage();

		Mockito.when(service.getPowerOutageById(Mockito.anyLong())).thenReturn(Optional.of(p));

		// Execute
		mockMvc.perform(get("/powerOutage/?id=2")).andDo(print())
				// Assert
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath("$.address").value("H9L023"));
	}

	@Test
	void deleteTest() throws Exception {

		// Setup
		PowerOutage p = TestData.createSamplePowerOutage();

		// execute
		Mockito.doNothing().when(service).deletePowerOutage(Mockito.anyLong());

		// Execution
		mockMvc.perform(delete("/powerOutage/?id=2")).andDo(print())
				// Assert
				.andExpect(status().isAccepted());

	}

}
