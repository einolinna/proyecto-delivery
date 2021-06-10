package com.delivery.BuenSabor.articuloMfactDetalle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.articuloMfactDetalle.entity.ArticuloMfactDetalle;
import com.delivery.BuenSabor.articuloMfactDetalle.repository.ArticuloMfactDetalleRepository;

@Service
public class ArticuloMfactDetalleServiceImpl implements ArticuloMfactDetalleService {

	@Autowired
	private ArticuloMfactDetalleRepository repository;
	
	@Override
	public Iterable<ArticuloMfactDetalle> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ArticuloMfactDetalle> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ArticuloMfactDetalle save(ArticuloMfactDetalle articuloMfactDetalle) {
		return repository.save(articuloMfactDetalle);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
