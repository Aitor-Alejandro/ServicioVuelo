package com.curso.service;

import java.util.List;

import com.curso.model.Vuelo;

public interface VueloService {
	List<Vuelo> vuelos();
	List<Vuelo> vuelos(int plazas);
	boolean reservar(long id, int plazas);
}
