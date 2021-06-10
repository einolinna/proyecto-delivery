package com.delivery.BuenSabor.cliente.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.cliente.entity.Cliente;
import com.delivery.BuenSabor.cliente.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	protected ClienteRepository clienteRepository;
	
	@Override
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

}
