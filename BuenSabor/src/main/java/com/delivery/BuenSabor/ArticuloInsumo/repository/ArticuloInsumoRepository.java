package com.delivery.BuenSabor.ArticuloInsumo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;

@Repository
public interface ArticuloInsumoRepository extends CrudRepository<ArticuloInsumo, Long> {

}
