package com.delivery.BuenSabor.MercadoPagoDatos.service;

import java.util.Optional;

import com.delivery.BuenSabor.MercadoPagoDatos.entiy.MercadoPagoDatos;

public interface MercadoPagoDatosService {

	public Iterable<MercadoPagoDatos> findAll();

	public Optional<MercadoPagoDatos> findById(Long idPago);

	public MercadoPagoDatos save(MercadoPagoDatos mPagoD);

	public void deleteById(Long id);
}
