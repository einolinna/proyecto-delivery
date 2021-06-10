package com.delivery.BuenSabor.MercadoPagoDatos.entiy;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.delivery.BuenSabor.Pedido.entity.Pedido;

@Entity
@Table(name = "mercado_pago_datos")
public class MercadoPagoDatos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago")
	private Long idPago;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "fecha_aprobacion")
	private Date fechaAprobacion;

	@Column(name = "forma_pago")
	private String formaPago;

	@Column(name = "metodo_pago")
	private String metodoPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_domicilio")
	private Pedido pedido;

	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MercadoPagoDatos)) {
			return false;
		}
		MercadoPagoDatos c = (MercadoPagoDatos) obj;
		return this.idPago != null && this.idPago.equals(c.getIdPago());
	}

	@Override
	public String toString() {
		String obj = "ID-Pago:" + this.idPago + "/ Fech-Creacion:" + this.fechaCreacion
				+ "/ Fecha Aprobacion:" + this.fechaAprobacion + "/ Forma pago:" + this.formaPago + "/ Metodo Pago:"
				+ this.metodoPago;
		return obj;
	}

}
