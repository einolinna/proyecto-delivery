package com.delivery.BuenSabor.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.security.enums.RolNombre;
import com.delivery.BuenSabor.usuario.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
