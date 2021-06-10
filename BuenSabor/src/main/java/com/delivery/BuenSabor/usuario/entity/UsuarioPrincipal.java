package com.delivery.BuenSabor.usuario.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.delivery.BuenSabor.cliente.entity.Cliente;

public class UsuarioPrincipal implements UserDetails{

	private String usuario;
	
	private String email;
	
	private String clave;
	
	private Cliente cliente;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UsuarioPrincipal(String usuario, String clave, Cliente cliente,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.cliente = cliente;
		this.authorities = authorities;
	}
	
	public UsuarioPrincipal(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.clave = password;
        this.authorities = authorities;
    }
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities =
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
						.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getUsuario(), usuario.getClave(), usuario.getCliente(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return clave;
	}
	
	@Override
	public String getUsername() {
		return usuario;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
