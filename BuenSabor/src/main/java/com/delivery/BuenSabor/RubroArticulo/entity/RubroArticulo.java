package com.delivery.BuenSabor.RubroArticulo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rubro_articulo")
public class RubroArticulo {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String denominacion;
	
	@Transient
	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "rubro_articulo")*/
	private List<RubroArticulo> rubroArticulo;

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

	public List<RubroArticulo> getRubroArticulo() {
		return rubroArticulo;
	}

	public void setRubroArticulo(List<RubroArticulo> rubroArticulo) {
		this.rubroArticulo = rubroArticulo;
	}
	
}
