package com.delivery.BuenSabor.usuario.service;

import java.util.Optional;

import com.delivery.BuenSabor.usuario.entity.Usuario;

public interface UsuarioService {

	public Iterable<Usuario> findAll();
	
	public Optional<Usuario> findByUsuario(String usuario);
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deleteById(Long id);
	
	public boolean existsByNombreUsuario(String nombreUsuario);
	
	public Optional<Usuario> getByEmail(String email);
	
	public void updatePassword(String password, String usuario);
	
	public boolean existsEmail(String email);
}
