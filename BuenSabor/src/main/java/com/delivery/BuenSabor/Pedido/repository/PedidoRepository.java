package com.delivery.BuenSabor.Pedido.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.Pedido.entity.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
