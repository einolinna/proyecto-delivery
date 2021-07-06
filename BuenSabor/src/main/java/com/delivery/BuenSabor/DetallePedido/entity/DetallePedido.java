package com.delivery.BuenSabor.DetallePedido.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.Pedido.entity.Pedido;
import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	private Long id;
	
	private int cantidad;
	
	private double subtotal;
	
	/*@ManyToOne( optional = false)
	@JoinColumn(name = "fk_articulo_insumo")*/
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo_insumo")
	private ArticuloInsumo articuloInsumo;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	/*@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "id_articulo_mfact")
	private ArticuloMfact articuloMfact;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido")
	private Pedido pedido;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

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
