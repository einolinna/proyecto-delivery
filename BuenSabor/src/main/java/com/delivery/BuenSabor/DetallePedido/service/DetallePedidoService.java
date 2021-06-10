package com.delivery.BuenSabor.DetallePedido.service;

import java.util.Optional;

import com.delivery.BuenSabor.DetallePedido.entity.DetallePedido;


public interface DetallePedidoService {

	public Iterable<DetallePedido> findAll();
	
	public Optional<DetallePedido> findById(Long id);
	
	public DetallePedido save(DetallePedido detalle_pedido);
	
	public void deleteById(Long id);
}

