package com.delivery.BuenSabor.ArticuloInsumo.service;

import java.util.Optional;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;

public interface ArticuloInsumoService {

	public Iterable<ArticuloInsumo> finAll();
	
	public Optional<ArticuloInsumo> findById(Long id);
	
	public ArticuloInsumo save(ArticuloInsumo articuloInsumo);
	
	public void deleteById(Long id);
	
	public Iterable<ArticuloInsumo> findByLike();
	
	public Iterable<ArticuloInsumo> findByInsumo();
}
