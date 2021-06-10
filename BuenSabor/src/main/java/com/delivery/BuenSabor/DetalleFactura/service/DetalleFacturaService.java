package com.delivery.BuenSabor.DetalleFactura.service;

import java.util.Optional;

import com.delivery.BuenSabor.DetalleFactura.entity.DetalleFactura;

public interface DetalleFacturaService {
	
	public Iterable<DetalleFactura> findAll();
	
	public Optional<DetalleFactura> findById(Long id);
	
	public DetalleFactura save(DetalleFactura detalle_factura);
	
	public void deleteById(Long id);
	

}
