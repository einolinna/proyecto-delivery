package com.delivery.BuenSabor.domicilio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.domicilio.entity.Domicilio;
import com.delivery.BuenSabor.domicilio.repository.DomicilioRepository;

@Service
public class DomicilioServiceImpl implements DomicilioService {

	@Autowired
	protected DomicilioRepository domicilioRepository;
	
	@Override
	public Iterable<Domicilio> findAll() {
		return domicilioRepository.findAll();
	}

	@Override
	public Optional<Domicilio> findById(Long id) {
		return domicilioRepository.findById(id);
	}

	@Override
	public Domicilio save(Domicilio domicilio) {
		return domicilioRepository.save(domicilio);
	}

	@Override
	public void deleteById(Long id) {
		domicilioRepository.deleteById(id);
	}

}
