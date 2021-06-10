package com.delivery.BuenSabor.articuloManufacturado.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;

@Repository
public interface ArticuloMfactRepository extends CrudRepository<ArticuloMfact, Long>{

}
