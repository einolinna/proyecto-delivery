package com.delivery.BuenSabor.DetallePedido.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	private Long id;
	
	private int cantidad;
	
	private double subtotal;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "fk_articulo_mfact")
	private ArticuloMfact articuloMfact;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "fk_articulo_insumo")
	private ArticuloInsumo articuloInsumo;

	public Long getId() {
		return id;
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

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof DetallePedido)) {
			return false;
		}
		DetallePedido dp = (DetallePedido) obj;
		return this.id !=null && this.id.equals(dp.getId());
	}
	
	@Override
	public String toString() {
		String obj = "ID: " + this.id
				+ "/ Cantidad: " + this.cantidad 
				+ "/ Subtotal: " + this.subtotal
				+ "/ " + this.articuloInsumo.toString()
				+ "/ " + this.articuloMfact.toString();
		return obj;
	}

}
