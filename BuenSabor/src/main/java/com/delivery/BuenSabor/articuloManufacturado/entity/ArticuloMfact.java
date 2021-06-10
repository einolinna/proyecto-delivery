package com.delivery.BuenSabor.articuloManufacturado.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.delivery.BuenSabor.articuloMfactDetalle.entity.ArticuloMfactDetalle;
import com.delivery.BuenSabor.rubroGeneral.entity.RubroGeneral;

@Entity
@Table(name = "articulo_manufacturado")
public class ArticuloMfact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tiempo_est_cocc")
	private int tiempoEstimadoCoccion;
	
	private String denominacion;
	
	@Column(name = "preio_venta")
	private double precioVenta;
	
	private String imagen;
	
	@Transient
	private RubroGeneral rubroGeneral;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticuloMfactDetalle> articulosMfactDetalle;
	
	public ArticuloMfact() {
		this.articulosMfactDetalle = new ArrayList<ArticuloMfactDetalle>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTiempoEstimadoCoccion() {
		return tiempoEstimadoCoccion;
	}

	public void setTiempoEstimadoCoccion(int tiempoEstimadoCoccion) {
		this.tiempoEstimadoCoccion = tiempoEstimadoCoccion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public RubroGeneral getRubroGeneral() {
		return rubroGeneral;
	}

	public void setRubroGeneral(RubroGeneral rubroGeneral) {
		this.rubroGeneral = rubroGeneral;
	}

	public List<ArticuloMfactDetalle> getArticulosMfactDetalle() {
		return articulosMfactDetalle;
	}

	public void setArticuloMfactDetalle(List<ArticuloMfactDetalle> articulosMfactDetalle) {
		this.articulosMfactDetalle = articulosMfactDetalle;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof ArticuloMfact)) {
			return false;
		}
		ArticuloMfact a = (ArticuloMfact) obj;
		return this.id !=null && this.id.equals(a.getId());
	}

	@Override
	public String toString() {
		String obj = "Id: " + this.id + "/ TiempEstimado: " + this.tiempoEstimadoCoccion +
				"/ Denominacion: " + this.denominacion + "/ PrecioVenta: " + this.precioVenta +
				"/ Imagen: " + this.imagen;
		for (ArticuloMfactDetalle articulosMfactDetalle : articulosMfactDetalle) {
			obj = obj + articulosMfactDetalle.toString();
		}
		return obj;
	}
	
	
}
