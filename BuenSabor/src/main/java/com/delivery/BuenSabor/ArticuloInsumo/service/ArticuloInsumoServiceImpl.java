package com.delivery.BuenSabor.ArticuloInsumo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.ArticuloInsumo.repository.ArticuloInsumoRepository;

@Service
public class ArticuloInsumoServiceImpl implements ArticuloInsumoService {

	@Autowired
	protected ArticuloInsumoRepository repository;
	
	@Override
	public Iterable<ArticuloInsumo> finAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ArticuloInsumo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ArticuloInsumo save(ArticuloInsumo articuloInsumo) {
		return repository.save(articuloInsumo);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Iterable<ArticuloInsumo> findByLike(){
		return repository.search();
	}

	@Override
	public Iterable<ArticuloInsumo> findByInsumo() {
		return repository.articuloForCliente();
	}
}
