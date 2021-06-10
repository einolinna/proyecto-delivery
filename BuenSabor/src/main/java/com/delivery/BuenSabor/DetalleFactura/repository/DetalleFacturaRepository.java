package com.delivery.BuenSabor.DetalleFactura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.DetalleFactura.entity.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends CrudRepository<DetalleFactura, Long> {

}
