package com.delivery.BuenSabor.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.BuenSabor.security.enums.RolNombre;
import com.delivery.BuenSabor.usuario.entity.Rol;
import com.delivery.BuenSabor.usuario.repository.RolRepository;

@Service
@Transactional
public class RolServiceImpl {

	@Autowired
	private RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}
	
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}
	
	public Iterable<Rol> getAll(){
		return rolRepository.findAll();
	}
}
