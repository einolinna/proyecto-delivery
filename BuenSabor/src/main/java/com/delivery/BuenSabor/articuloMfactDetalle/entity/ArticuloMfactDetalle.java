package com.delivery.BuenSabor.articuloMfactDetalle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;

@Entity
@Table(name = "articulo_mfact_detalle")
public class ArticuloMfactDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double cantidad;
	
	@Column(name = "unidad_medida")
	private String unidadMedida;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_articulo_insumo")
	private ArticuloInsumo articuloInsumo;

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
