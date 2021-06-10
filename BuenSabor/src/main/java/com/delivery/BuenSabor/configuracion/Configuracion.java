package com.delivery.BuenSabor.configuracion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "configuracion")
public class Configuracion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "cantidad_cocinero")
	private int cantidadCocinero;
	
	private String emailEmpresa;
	
	private String tokenMercadoPago;

	//@Query("SELECT * FROM configuracion.cantidad_cocinero;")
	public int getCantidadCocinero() {
		return cantidadCocinero;
	}

	public void setCantidadCocinero(int cantidadCocinero) {
		this.cantidadCocinero = cantidadCocinero;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public String getTokenMercadoPago() {
		return tokenMercadoPago;
	}

	public void setTokenMercadoPago(String tokenMercadoPago) {
		this.tokenMercadoPago = tokenMercadoPago;
	}
	
}
