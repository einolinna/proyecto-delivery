package com.delivery.BuenSabor.articuloMfactDetalle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.articuloMfactDetalle.entity.ArticuloMfactDetalle;

@Repository
public interface ArticuloMfactDetalleRepository extends CrudRepository<ArticuloMfactDetalle, Long> {

}
