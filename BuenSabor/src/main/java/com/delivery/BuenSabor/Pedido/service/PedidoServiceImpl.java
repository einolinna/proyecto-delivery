package com.delivery.BuenSabor.Pedido.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.Pedido.entity.Pedido;
import com.delivery.BuenSabor.Pedido.repository.PedidoRepository;


@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	protected PedidoRepository pedidoRepositoy;

	@Override
	public Iterable<Pedido> findAll() {
		return pedidoRepositoy.findAll();
	}

	@Override
	public Optional<Pedido> findById(Long id) {
		return pedidoRepositoy.findById(id);
	}

	@Override
	public Pedido save(Pedido pedido) {
		return pedidoRepositoy.save(pedido);
	}

	@Override
	public void deleteById(Long id) {
		pedidoRepositoy.deleteById(id);
	}

}
