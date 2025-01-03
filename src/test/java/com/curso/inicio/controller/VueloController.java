package com.curso.inicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Vuelo;
import com.curso.service.VueloService;
@SpringBootTest
@AutoConfigureMockMvc
class VueloController {
	@MockitoBean
	private VueloService service;
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		Vuelo vuelo = new Vuelo(1,"ryanair", new Date(System.currentTimeMillis()), 99.99d, 9);
		when(service.findById(1)).thenReturn(vuelo);
		when(service.vuelos()).thenReturn(Arrays.asList(vuelo));
	}
	@Test
	void findAlltest() throws Exception {
		mockMvc.perform(get("/api/vuelos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].idVuelo", is(1)));
		
	}
	@Test
	void findByPlazasTest() throws Exception {
		mockMvc.perform(get("/api/vuelos/1"))
			.andExpect(status().isOk());
		mockMvc.perform(get("/api/vuelos/99"))
			.andExpect(status().isNotFound());
	}
}
