package com.delivery.BuenSabor.articuloMfactDetalle.service;

import java.util.Optional;

import com.delivery.BuenSabor.articuloMfactDetalle.entity.ArticuloMfactDetalle;

public interface ArticuloMfactDetalleService {

	public Iterable<ArticuloMfactDetalle> findAll();
	
	public Optional<ArticuloMfactDetalle> findById(Long id);
	
	public ArticuloMfactDetalle save(ArticuloMfactDetalle articuloMfactDetalle);
	
	public void deleteById(Long id);
}
