package com.delivery.BuenSabor.Factura.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.delivery.BuenSabor.DetalleFactura.entity.DetalleFactura;
import com.delivery.BuenSabor.Pedido.entity.Pedido;
import com.delivery.BuenSabor.usuario.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "factura")
public class Factura {

	@Id
	@Column(name = "numero", unique = true)
	private Integer numero;
	
	@Column(name = "monto_descuento")
	private double montoDescuento;
	
	@Column(name = "forma_pago")
	private String formaPago;
	
	@Column(name = "numero_tarjeta")
	private String numTarjeta;
	
	@Column(name = "total_venta")
	private double totalVenta;
	
	@Column(name = "total_costo")
	private double totalCosto;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	@OneToMany(mappedBy="factura",fetch = FetchType.LAZY)
	private List<DetalleFactura> detallesFacturas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_pedido")
	private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	public Factura() {
		this.detallesFacturas = new ArrayList<DetalleFactura>();
	}
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public double getTotalCosto() {
		return totalCosto;
	}

	public void setTotalCosto(double totalCosto) {
		this.totalCosto = totalCosto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<DetalleFactura> getDetallesFacturas() {
		return detallesFacturas;
	}

	public void setDetallesFacturas(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;	
		}
		if(!(obj instanceof Factura)) {
			return false;
		}
		Factura f = (Factura) obj;
		return this.numero != null && this.numero.equals(f.getNumero());
	}

	@Override
	public String toString() {
		String obj = "Numero: " + this.numero
				+ "/ Fecha: " + this.fecha.toString()
				+ "/ Descuento: " + this.montoDescuento
				+ "/ FormaPago: " + this.formaPago
				+ "/ NroTarjeta: " + this.numTarjeta
				+ "/ TotalVenta: " + this.totalVenta
				+ "/ totalCosto: " + this.totalCosto
				+ "/ Pedido: " + this.pedido.getId();
		for (DetalleFactura detalleFactura : detallesFacturas) {
			obj = obj + detalleFactura.toString();
		}
		return obj;
	}

}
