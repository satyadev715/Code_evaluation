package com.adex.assessment.codeevaluation;

import com.adex.assessment.codeevaluation.entity.Customer;
import com.adex.assessment.codeevaluation.entity.RequestDTO;
import com.adex.assessment.codeevaluation.filter.BlackListedUserAgentException;
import com.adex.assessment.codeevaluation.filter.CustomerNotFoundException;
import com.adex.assessment.codeevaluation.service.CustomerService;
import com.adex.assessment.codeevaluation.service.RequestDataService;
import com.adex.assessment.codeevaluation.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.InetAddress;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CustomerRequestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private RequestDataService requestDataService;

	@MockBean
	private ValidationService validationService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testProcessRequest() throws Exception {
		InetAddress remoteIp = InetAddress.getByName("123.234.56.78");
		RequestDTO requestDTO = new RequestDTO(1,2,"aaaaaaaa-bbbb-cccc-1111-222222222222",remoteIp,1500000000L);

		Customer customer = new Customer(1,"Big News Media Corp",1);
		given(customerService.getCustomer(1)).willReturn(customer);

		given(validationService.validateUserAgent("PostmanRuntime/7.26.2")).willReturn(false);
		given(validationService.validateRemoteIp("123.234.56.78")).willReturn(false);
		given(requestDataService.saveRequest(requestDTO,'Y')).willReturn(75);

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/request")
				.header("User-Agent","PostmanRuntime/7.26.2")
				.content(asJsonString(requestDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testProcessRequestForCustomerNotFoundException() throws Exception{
		InetAddress remoteIp = InetAddress.getByName("123.234.56.78");
		RequestDTO requestDTO = new RequestDTO(5,2,"aaaaaaaa-bbbb-cccc-1111-222222222222",remoteIp,1500000000L);
		given(customerService.getCustomer(5)).willThrow(new CustomerNotFoundException("CustomerId not found "));

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/request")
				.content(asJsonString(requestDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));
	}
	@Test
	public void testGetCustomerRequestStatistics() throws Exception{

		given(customerService.getCustomer(5)).willThrow(new CustomerNotFoundException("CustomerId not found "));

		mockMvc.perform(MockMvcRequestBuilders.
				get("/customer/statistics?id=5&requestDate=2020.07.24"))
				.andExpect(result ->
						assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));
	}

	@Test
	public void testProcessRequestForNotActiveException() throws Exception{
		LocalDate date = LocalDate.of(2020, 7, 24);
		given(requestDataService.getCountOfRequests(1,date)).willReturn(2L);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/statistics?id=1&requestDate=2020.07.24")
		.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.requestCount").value(2));
	}

	@Test
	public void testProcessRequestForBlackListedUserAgentException() throws Exception {
		InetAddress remoteIp = InetAddress.getByName("213.070.64.33");
		RequestDTO requestDTO = new RequestDTO(1,2,"aaaaaaaa-bbbb-cccc-1111-222222222222",remoteIp,1500000000L);

		Customer customer = new Customer(1,"Big News Media Corp",1);
		given(customerService.getCustomer(1)).willReturn(customer);
		given(validationService.validateUserAgent("Googlebot")).willReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/request")
				.header("User-Agent","Googlebot")
				.content(asJsonString(requestDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof BlackListedUserAgentException));
	}
	public static String asJsonString(RequestDTO obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
