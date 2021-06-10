package com.delivery.BuenSabor.Factura.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.Factura.entity.Factura;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Integer> {

}
