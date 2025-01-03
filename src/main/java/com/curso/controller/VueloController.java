package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Vuelo;
import com.curso.service.VueloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador Rest de la api del microservicio de vuelos de aviones
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@Tag(name="Servicio de vuelos", description = "API para las operaciones relacionadas con el servicio de vuelos")
@RestController
@RequestMapping(value="/api/vuelos")
public class VueloController {
	@Autowired
	VueloService service;
	@Autowired
	RestTemplate template;
	
	@Operation(summary = "Obtencion de vuelos", description = "Devuelve todos los vuelos existentes en la base de datos", responses = {
			@ApiResponse(responseCode = "200", description = "Se han obtenido todos los vuelos")
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vuelo>> vuelos(){
		return new ResponseEntity<>(service.vuelos(), HttpStatus.OK);
	}
	
	@Operation(summary = "Obtencion de vuelos segun plazas", description = "Devuelve todos los vuelos que disponen del numero de plazas libres" + 
	" solicitado o más", responses = {
			@ApiResponse(responseCode = "404", description = "No hay ningun vuelo que disponga del numero de plazas solicitado"),
			@ApiResponse(responseCode = "200", description = "Devuele los vuelos que cumplen con la condicion de tener plazas sufucientes")
	})
	@GetMapping(value="/plazas/{plazas}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vuelo>> vuelos(@Parameter(
			name = "plazas",
			description = "Número de plazas por el que se consulta",
			required = true)
		@PathVariable int plazas){
		System.out.println(plazas);
		List<Vuelo> listVuelos = service.vuelos(plazas);
		
		HttpStatus responseStatus = listVuelos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		
		return new ResponseEntity<>(listVuelos, responseStatus);
	}
	@Operation(summary = "Reserva cierto numero de plazas para un vuelo", description = "Si el vuelo determinado existe y dispone de las plazas" + 
	" suficientes para reservar el número indicado, entonces el actualiza el vuelo para registrar el cambio", responses = {
			@ApiResponse(responseCode = "202", description = "Es posible realizar la reserva, por tanto la peticion de reserva se he aceptado"),
			@ApiResponse(responseCode = "400", description = "No es posible registrar la reserva porque no se cumplen las condiciones para ello")
	})
	@PutMapping(value="/{idVuelo}/{plazas}")
	public ResponseEntity<Void> reservar(
			@Parameter(
					name = "idVuelo",
					description = "Código identificador del vuelo",
					required = true)
			@PathVariable long idVuelo,
			@Parameter(
					name = "plazas",
					description = "Numero de plazas que se desea reservar",
					required = true)
			@PathVariable int plazas){
		HttpStatus responseStatus = service.reservar(idVuelo, plazas) ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(responseStatus);
	}

}
