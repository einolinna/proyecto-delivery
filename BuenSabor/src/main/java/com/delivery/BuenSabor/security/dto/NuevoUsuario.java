package com.delivery.BuenSabor.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.delivery.BuenSabor.cliente.entity.Cliente;

public class NuevoUsuario {

	@NotBlank
	private String usuario;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private Set<String> roles = new HashSet<>();
	
	private Cliente cliente;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
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
