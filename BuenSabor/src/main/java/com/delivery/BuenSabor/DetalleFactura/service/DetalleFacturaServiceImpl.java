package com.delivery.BuenSabor.DetalleFactura.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.DetalleFactura.entity.DetalleFactura;
import com.delivery.BuenSabor.DetalleFactura.repository.DetalleFacturaRepository;


@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService {
	
	@Autowired
	protected DetalleFacturaRepository DetalleFacturaRepository;
	
	@Override
	public Iterable<DetalleFactura> findAll() {
		return DetalleFacturaRepository.findAll();
	}

	@Override
	public Optional<DetalleFactura> findById(Long id) {
		return DetalleFacturaRepository.findById(id);
	}

	@Override
	public DetalleFactura save(DetalleFactura detalle_factura) {
		return DetalleFacturaRepository.save(detalle_factura);
	}

	@Override
	public void deleteById(Long id) {
		DetalleFacturaRepository.deleteById(id);
	}
}

