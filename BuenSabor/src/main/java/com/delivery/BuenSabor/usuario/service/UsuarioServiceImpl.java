package com.delivery.BuenSabor.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.BuenSabor.usuario.entity.Usuario;
import com.delivery.BuenSabor.usuario.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	protected UsuarioRepository usuarioRepository;
	
	@Override
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findByUsuario(String usuario) {
		return usuarioRepository.findByNombreUsuario(usuario);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);

	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	@Override
	public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

	@Override
	public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

	@Override
    public boolean existsEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

	@Override
	public void updatePassword(String password, String usuario) {
		usuarioRepository.updatePassword(password, usuario);
	}
}
