package com.curso.controller.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Vuelo;
import com.curso.service.VueloService;

class TestVueloController {
	@MockitoBean
	private VueloService service;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		Vuelo vuelo = new Vuelo(1,"ryanair",new Date(System.currentTimeMillis()), 55.5, 12);
		//when(service.vueloById(1).thenReturn(vuelo));
		when(service.vuelos()).thenReturn(Arrays.asList(vuelo));
		
	}
	
	
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
}
