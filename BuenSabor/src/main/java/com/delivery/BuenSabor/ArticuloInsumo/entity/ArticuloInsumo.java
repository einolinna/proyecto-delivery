package com.delivery.BuenSabor.ArticuloInsumo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.RubroArticulo.entity.RubroArticulo;

@Entity
@Table(name = "articulo_insumo")
public class ArticuloInsumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String denominacion;
	
	@Column(name = "precio_compra")
	private double precioCompra;
	
	@Column(name = "precio_venta")
	private double precioVenta;
	
	@Column(name = "stock_actual")
	private double stockActual;
	
	@Column(name = "stock_minimo")
	private double stockMinimo;
	
	@Column(name = "unidad_medida")
	private String unidadMedida;
	
	@Column(name = "es_insumo")
	private boolean esInsumo;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "fk_rubro_articulo")
	private RubroArticulo rubroArticulo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getStockActual() {
		return stockActual;
	}

	public void setStockActual(double stockActual) {
		this.stockActual = stockActual;
	}

	public double getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public boolean getEsInsumo() {
		return esInsumo;
	}

	public void setEsInsumo(boolean esInsumo) {
		this.esInsumo = esInsumo;
	}

	public RubroArticulo getRubroArticulo() {
		return rubroArticulo;
	}

	public void setRubroArticulo(RubroArticulo rubroArticulo) {
		this.rubroArticulo = rubroArticulo;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof ArticuloInsumo)) {
			return false;
		}
		ArticuloInsumo a = (ArticuloInsumo) obj;
		return this.id !=null && this.id.equals(a.getId());
	}

	@Override
	public String toString() {
		String obj = "Id: " + this.id +
				"/ Denominacion: " + this.denominacion +
				"/ PrecioCompra: " + this.precioCompra +
				"/ PrecioVenta: " + this.precioVenta +
				"/ StockActual: " + this.stockActual +
				"/ StockMinimo: " + this.stockMinimo +
				"/ UnidadDeMedida: " + this.unidadMedida +
				"/ EsInsumo: " + this.esInsumo +
				"/ RubroArticulo: " + this.rubroArticulo.getDenominacion();
		return obj;
	}
	
	
}
