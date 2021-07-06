package com.delivery.BuenSabor.articuloManufacturado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;

@EnableJpaRepositories
@Repository
public interface ArticuloMfactRepository extends JpaRepository<ArticuloMfact, Long>{

	@Query(value = "SELECT * FROM articulo_manufacturado",nativeQuery = true)
	public Iterable<ArticuloMfact> search();
}
