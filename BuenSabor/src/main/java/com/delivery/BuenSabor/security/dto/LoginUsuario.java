package com.delivery.BuenSabor.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {

	@NotBlank
	private String usuario;
	
	@NotBlank
	private String password;

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
	
}
