package com.delivery.BuenSabor.cliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.cliente.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
