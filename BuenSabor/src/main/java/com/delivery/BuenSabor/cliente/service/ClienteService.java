package com.delivery.BuenSabor.cliente.service;

import java.util.Optional;

import com.delivery.BuenSabor.cliente.entity.Cliente;

public interface ClienteService {

	public Iterable<Cliente> findAll();
	
	public Optional<Cliente> findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void deleteById(Long id);
}
