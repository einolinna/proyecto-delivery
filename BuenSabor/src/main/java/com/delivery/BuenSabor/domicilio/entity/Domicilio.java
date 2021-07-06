package com.delivery.BuenSabor.domicilio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domicilio")
public class Domicilio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	private String localidad;
	
	private String calle;
	
	private int numero;
	



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}


	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof Domicilio)) {
			return false;
		}
		Domicilio d = (Domicilio)obj;
		return this.id !=null && this.id.equals(d.getId());
	}

	@Override
	public String toString() {
		String obj="Id:" + this.id + "/ Localidad:" + this.localidad + "/ Calle:" + this.calle +  "/ Numero:" + this.numero  ;
		return obj;
	}
	
	
}
