package com.delivery.BuenSabor.domicilio.service;

import java.util.Optional;

import com.delivery.BuenSabor.domicilio.entity.Domicilio;

public interface DomicilioService {

	public Iterable<Domicilio> findAll();
	
	public Optional<Domicilio> findById(Long id);
	
	public Domicilio save(Domicilio domicilio);
	
	public void deleteById(Long id);
}
