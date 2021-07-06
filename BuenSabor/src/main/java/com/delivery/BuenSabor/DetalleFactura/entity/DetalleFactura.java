package com.delivery.BuenSabor.DetalleFactura.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.Factura.entity.Factura;
import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {
	
	@Id
	private Long id;
	
	private int cantidad;
	
	private double subtotal;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo_mfact")
	private ArticuloMfact articuloMfact;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo_insumo")
	private ArticuloInsumo articuloInsumo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero")
	private Factura factura;

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public ArticuloMfact getArticuloMfact() {
		return articuloMfact;
	}

	public void setArticuloMfact(ArticuloMfact articuloMfact) {
		this.articuloMfact = articuloMfact;
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
		if(!(obj instanceof DetalleFactura)) {
			return false;
		}
		DetalleFactura DF = (DetalleFactura) obj;
		return this.id !=null && this.id.equals(DF.getId());
	}
	
	@Override
	public String toString() {
		String obj = "ID:" + this.id
				+ "/ Cantidad: " + this.cantidad 
				+ "/ Subtotal: " + this.subtotal;
		return obj;
	}

}
