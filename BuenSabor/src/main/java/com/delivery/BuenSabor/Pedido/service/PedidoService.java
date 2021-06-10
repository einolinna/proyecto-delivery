package com.delivery.BuenSabor.Pedido.service;

import java.util.Optional;

import com.delivery.BuenSabor.Pedido.entity.Pedido;

public interface PedidoService {
	
	public Iterable<Pedido> findAll();

	public Optional<Pedido> findById(Long id);

	public Pedido save(Pedido pedido);

	public void deleteById(Long id);
}
