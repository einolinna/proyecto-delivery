package com.delivery.BuenSabor.DetallePedido.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.DetallePedido.entity.DetallePedido;
import com.delivery.BuenSabor.DetallePedido.repository.DetallePedidoRepository;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {
	
	@Autowired
	protected DetallePedidoRepository DetallePedidoRepository;
	
	@Override
	public Iterable<DetallePedido> findAll() {
		return DetallePedidoRepository.findAll();
	}

	@Override
	public Optional<DetallePedido> findById(Long id) {
		return DetallePedidoRepository.findById(id);
	}

	@Override
	public DetallePedido save(DetallePedido detalle_pedido) {
		return DetallePedidoRepository.save(detalle_pedido);
	}

	@Override
	public void deleteById(Long id) {
		DetallePedidoRepository.deleteById(id);
	}

}
