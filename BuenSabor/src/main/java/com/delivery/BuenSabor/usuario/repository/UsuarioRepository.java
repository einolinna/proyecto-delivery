package com.delivery.BuenSabor.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.delivery.BuenSabor.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNombreUsuario(String nombreUsuario); 
	
	boolean existsByNombreUsuario(String nombreUsuario);
	
	@Query(value = "SELECT * FROM usuario WHERE usuario.email = ?1", nativeQuery = true)
	Optional<Usuario> findByEmail(String email);
	
	@Query(value = "UPDATE usuario SET usuario.clave = ?1 where usuario.usuario = ?2", nativeQuery = true)
	void updatePassword(String pass, String usuario);
    
	boolean existsByEmail(String email);
	
}
