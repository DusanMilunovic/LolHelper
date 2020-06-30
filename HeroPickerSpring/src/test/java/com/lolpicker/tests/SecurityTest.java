package com.lolpicker.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

	@Autowired
	private MockMvc mockMvc;	

	@Test
	public void testDdos() throws Exception {
		for (int i = 0; i < 30; i++) {
			this.mockMvc.perform(get("http://localhost:9090/relations/Ashe/Lissandra")).andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Enemies"));
		}
		this.mockMvc.perform(get("http://localhost:9090/relations/Ashe/Lissandra")).andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Suspicious activity detected from your IP address. Too many requests sent"));
	}
}
