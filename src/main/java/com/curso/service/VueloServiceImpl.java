package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.model.Vuelo;
import com.curso.repository.VueloRepository;
/**
 * Implementacion de la lógica de negocio del servicio de vuelos
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@Service
public class VueloServiceImpl implements VueloService {
	@Autowired
	VueloRepository repository;
	
	@Override
	public List<Vuelo> vuelos() {
		return repository.findAll();
	}

	@Override
	public List<Vuelo> vuelos(int plazas) {
		return repository.findByPlazasDisponiblesGreaterThanEqual(plazas);
	}

	@Override
	public boolean reservar(long id, int plazas) {
		Vuelo vuelo = repository.findById(id).orElse(null);
		if (vuelo==null)
			return false;
		if (vuelo.getPlazasDisponibles() < plazas)
			return false;
		vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazas);
		repository.save(vuelo);
		return true;
	}

	
}
