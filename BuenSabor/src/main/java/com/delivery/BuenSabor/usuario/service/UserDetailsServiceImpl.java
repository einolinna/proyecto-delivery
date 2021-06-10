package com.delivery.BuenSabor.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.delivery.BuenSabor.usuario.entity.Usuario;
import com.delivery.BuenSabor.usuario.entity.UsuarioPrincipal;
import com.delivery.BuenSabor.usuario.entity.UsuarioPrincipalFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioServiceImpl.findByUsuario(nombreUsuario).get();
		return UsuarioPrincipal.build(usuario);
	}
	
    public UserDetails loadUserByUsernameEmail(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioServiceImpl.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email no encontrado"));
        return UsuarioPrincipalFactory.build(usuario);
    }

}
