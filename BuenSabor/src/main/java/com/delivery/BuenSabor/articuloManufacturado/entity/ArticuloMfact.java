package com.delivery.BuenSabor.articuloManufacturado.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.delivery.BuenSabor.articuloMfactDetalle.entity.ArticuloMfactDetalle;
import com.delivery.BuenSabor.rubroGeneral.entity.RubroGeneral;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "articulo_manufacturado")
public class ArticuloMfact{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_articulo_mfact")
	private Long id;
	
	@Column(name = "tiempo_est_cocc")
	private int tiempoEstimadoCoccion;
	
	private String denominacion;
	
	@Column(name = "precio_venta")
	private double precioVenta;
	
	private String imagen;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "fk_rubro_general")
	private RubroGeneral rubroGeneral;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy="articuloMfact")
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
