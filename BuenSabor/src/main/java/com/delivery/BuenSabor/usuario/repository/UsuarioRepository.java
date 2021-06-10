package com.delivery.BuenSabor.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsuario(String usuario); 
	
	boolean existsByUsuario(String usuario);
	
	Optional<Usuario> findByEmail(String email);
    
	boolean existsByEmail(String email);
	
}
