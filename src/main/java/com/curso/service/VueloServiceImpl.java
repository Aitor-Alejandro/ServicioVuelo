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
	/**
	 * @return listVuelos lista de todos los vuelos existentes
	 */
	@Override
	public List<Vuelo> vuelos() {
		List<Vuelo> listVuelos = repository.findAll();
		return listVuelos;
	}

	/**
	 * @param plazas El numero de plazas que debe tener, como minimo, el vuelo
	 * @return listVuelos Lista de vuelos con el numero indicado de plazas
	 */
	@Override
	public List<Vuelo> vuelos(int plazas) {
		List<Vuelo> listVuelos = repository.findByPlazasDisponiblesGreaterThanEqual(plazas);
		return listVuelos;
	}

	/**
	 * @param id Identificador unico del vuelo
	 * @param plazas Numero de plazas que se desean reservar del vuelo
	 * @return booleano que indica si es posible realizar la reserva o no
	 */
	@Override
	public boolean reservar(long id, int plazas) {
		Vuelo vuelo = repository.findById(id).orElse(null);
		if (vuelo==null) {
			return false;
		}
		if (vuelo.getPlazasDisponibles() < plazas) {
			return false;
		}
		vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazas);
		repository.save(vuelo);
		return true;
	}

	/**
	 * @param id Identidicador del vuelos
	 * @return vuelo relacionado con el id enviado, null en caso de no existir
	 */
	@Override
	public Vuelo findById(long id) {
		Vuelo vuelo = repository.findById(id).orElse(null);
		return vuelo;
	}

	
}
