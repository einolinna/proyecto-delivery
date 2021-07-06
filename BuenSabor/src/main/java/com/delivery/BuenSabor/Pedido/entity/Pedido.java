package com.delivery.BuenSabor.Pedido.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.delivery.BuenSabor.DetallePedido.entity.DetallePedido;
import com.delivery.BuenSabor.Factura.entity.Factura;
import com.delivery.BuenSabor.MercadoPagoDatos.entiy.MercadoPagoDatos;
import com.delivery.BuenSabor.cliente.entity.Cliente;
import com.delivery.BuenSabor.domicilio.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String estado;
	
	@Column(name = "horas_estimadas_fin")
	private Date horaEstimadaFin; // dataTime
	
	@Column(name = "tipo_envio")
	private String tipoEnvio;

	private double total;
	
	private Date fecha;

	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@OneToMany(mappedBy="pedido",fetch = FetchType.LAZY)
	 private List<DetallePedido> detallesPedido;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_factura")
	private Factura factura;
	
	@ManyToOne
	@JoinColumn( name = "fk_cliente")
	private Cliente cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "fk_domicilio")
	private Domicilio domicilio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "fk_mercado_pago_datos")
	public MercadoPagoDatos mercadoPagoDatos;
	
	public Pedido() {
		this.detallesPedido= new ArrayList<DetallePedido>();
	}
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHoraEstimadaFin() {
		return horaEstimadaFin;
	}

	public void setHoraEstimadaFin(Date horaEstimadaFin) {
		this.horaEstimadaFin = horaEstimadaFin;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Factura getFactura() {
		return factura;
	}

	public String getEstado() {
		return estado;
	}

	public String getTipoEnvio() {
		return tipoEnvio;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public List<DetallePedido> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(List<DetallePedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	public MercadoPagoDatos getMercadoPagoDatos() {
		return mercadoPagoDatos;
	}

	public void setMercadoPagoDatos(MercadoPagoDatos mercadoPagoDatos) {
		this.mercadoPagoDatos = mercadoPagoDatos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;	
		}
		if(!(obj instanceof Pedido)) {
			return false;
		}
		Pedido p = (Pedido) obj;
		return this.id != null && this.id.equals(p.getId());
	}

	@Override
	public String toString() {
		String obj = "NumeroPedido: "+  this.id 
				+ "/ Estado: " + this.estado 
				+ "/ TipoEnvio: " + this.tipoEnvio 
				+ "/ Total: " + this.total 
				+ "/ MercadoPago " + this.mercadoPagoDatos.toString()
				+ "/ Cliente: " +this.cliente.getNombre() 
				+ "/ Domicilio: " + this.domicilio.getLocalidad() + "-" + this.domicilio.getNumero() 
				+ "/ FacturaId: " + this.factura.getNumero() 
				+ "/ Fecha: " +this.fecha.toString() 
				+ "/ HoraEstimada: " + this.horaEstimadaFin.toString();
		for (DetallePedido detallePedido : detallesPedido) {
			obj = obj + detallePedido.toString();
		}
		return obj;
	}
	
	
	
	

}
