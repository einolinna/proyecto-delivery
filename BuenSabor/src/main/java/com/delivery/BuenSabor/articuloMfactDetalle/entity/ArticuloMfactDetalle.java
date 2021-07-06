package com.delivery.BuenSabor.articuloMfactDetalle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "articulo_mfact_detalle")
public class ArticuloMfactDetalle{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double cantidad;
	
	@Column(name = "unidad_medida")
	private String unidadMedida;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo_insumo")
	private ArticuloInsumo articuloInsumo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo_mfact")
	private ArticuloMfact articuloMfact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public ArticuloInsumo getArticuloInsumo() {
		return articuloInsumo;
	}

	public void setArticuloInsumo(ArticuloInsumo articuloInsumo) {
		this.articuloInsumo = articuloInsumo;
	}

	public ArticuloMfact getArticuloMfact() {
		return articuloMfact;
	}

	public void setArticuloMfact(ArticuloMfact articuloMfact) {
		this.articuloMfact = articuloMfact;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof ArticuloMfactDetalle)) {
			return false;
		}
		ArticuloMfactDetalle a = (ArticuloMfactDetalle) obj;
		return this.id != null && this.id.equals(a.getId());
	}

	@Override
	public String toString() {
		String obj = "Id: " + this.id + "/ UnidadDeMedida: " + this.unidadMedida + "/ Cantidad: " + this.cantidad; 
		return obj;
	}
	
	
}
