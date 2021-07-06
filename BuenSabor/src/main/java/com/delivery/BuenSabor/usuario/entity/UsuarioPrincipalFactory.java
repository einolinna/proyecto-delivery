package com.delivery.BuenSabor.usuario.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioPrincipalFactory {

	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> 
                new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getEmail(), usuario.getPassword(), null, null, authorities);
	}
}
