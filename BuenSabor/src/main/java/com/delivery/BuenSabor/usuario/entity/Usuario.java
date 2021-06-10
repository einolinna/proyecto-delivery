package com.delivery.BuenSabor.usuario.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.delivery.BuenSabor.cliente.entity.Cliente;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usuario", unique = true)
	private String usuario;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String clave;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	
	public Usuario() {}
	
	public Usuario(String email, String password) {
		this.email = email;
		this.clave = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
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

	public boolean login(Usuario user) {
		if(this.usuario.equals(user.getUsuario())) {
			if(this.clave.equals(user.getClave()))
				return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof Usuario)) {
			return false;
		}
		Usuario u = (Usuario) obj;
		return this.usuario !=null && this.usuario.equals(u.getUsuario());
	}

	@Override
	public String toString() {
		String obj = "Usuario: "+this.usuario + "/ clave: " + this.clave +
				"/ clave: " + this.clave;
		return obj;
	}
	
	
}
