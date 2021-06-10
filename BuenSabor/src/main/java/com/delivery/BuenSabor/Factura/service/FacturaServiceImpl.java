package com.delivery.BuenSabor.Factura.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.Factura.entity.Factura;
import com.delivery.BuenSabor.Factura.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	protected FacturaRepository facturaRepository;

	@Override
	public Iterable<Factura> findAll() {
		return facturaRepository.findAll();
	}

	@Override
	public Optional<Factura> findByNumero(Integer numero) {
		return facturaRepository.findById(numero);
	}

	@Override
	public Factura save(Factura factura) {
		return facturaRepository.save(factura);
	}

	@Override
	public void deleteByNumero(Integer numero) {
		facturaRepository.deleteById(numero);
	}

}
