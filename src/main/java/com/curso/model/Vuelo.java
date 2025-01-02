package com.curso.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase POJO entidad descriptora de la tabla 'vuelos' de la base de datos
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@Entity
@Table(name="vuelos")
public class Vuelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVuelo;
	private String company;
	@Column(name="fecha_vuelo")
	private Date fechaVuelo;
	private double precio;
	@Column(name="plazas_disponibles")
	private int plazasDisponibles;
	
	public Vuelo() {
		super();
	}
	
	public Vuelo(long idVuelo) {
		super();
		this.idVuelo = idVuelo;
	}
	
	public Vuelo(long idVuelo, String company, Date fechaVuelo, double precio, int plazasDisponibles) {
		super();
		this.idVuelo = idVuelo;
		this.company = company;
		this.fechaVuelo = fechaVuelo;
		this.precio = precio;
		this.plazasDisponibles = plazasDisponibles;
	}

	public long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(long idVuelo) {
		this.idVuelo = idVuelo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getFechaVuelo() {
		return fechaVuelo;
	}

	public void setFechaVuelo(Date fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}
	
}
