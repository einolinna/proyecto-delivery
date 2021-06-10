package com.delivery.BuenSabor.DetallePedido.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.DetallePedido.entity.DetallePedido;

@Repository
public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Long> {

}
