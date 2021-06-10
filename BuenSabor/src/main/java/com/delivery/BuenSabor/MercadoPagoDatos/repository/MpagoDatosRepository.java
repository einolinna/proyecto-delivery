package com.delivery.BuenSabor.MercadoPagoDatos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.MercadoPagoDatos.entiy.MercadoPagoDatos;

@Repository
public interface MpagoDatosRepository extends CrudRepository<MercadoPagoDatos, Long > {

}
