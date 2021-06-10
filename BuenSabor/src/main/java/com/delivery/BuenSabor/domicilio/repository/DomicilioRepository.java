package com.delivery.BuenSabor.domicilio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.domicilio.entity.Domicilio;

@Repository
public interface DomicilioRepository extends CrudRepository<Domicilio, Long>{

}
