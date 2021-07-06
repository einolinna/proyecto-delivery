package com.delivery.BuenSabor.ArticuloInsumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;

@EnableJpaRepositories
@Repository
public interface ArticuloInsumoRepository extends JpaRepository<ArticuloInsumo, Long> {

	@Query(value = "SELECT * FROM articulo_insumo", nativeQuery = true)
	public Iterable<ArticuloInsumo> search();
	
	@Query(value = "SELECT * FROM articulo_insumo WHERE es_insumo = false AND stock_actual >0", nativeQuery = true)
	public Iterable<ArticuloInsumo> articuloForCliente();
}
