package com.delivery.BuenSabor.Factura.service;

import java.util.Optional;

import com.delivery.BuenSabor.Factura.entity.Factura;

public interface FacturaService {

	public Iterable<Factura> findAll();

	public Optional<Factura> findByNumero(Integer numero);

	public Factura save(Factura factura);

	public void deleteByNumero(Integer numero);
}
