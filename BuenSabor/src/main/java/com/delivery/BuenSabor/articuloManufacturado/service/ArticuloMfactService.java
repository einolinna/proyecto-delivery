package com.delivery.BuenSabor.articuloManufacturado.service;

import java.util.Optional;

import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;

public interface ArticuloMfactService {

	public Iterable<ArticuloMfact> findAll();
	
	public Optional<ArticuloMfact> findById(Long id);
	
	public ArticuloMfact save(ArticuloMfact articuloMfact);
	
	public void deleteById(Long id);
}
