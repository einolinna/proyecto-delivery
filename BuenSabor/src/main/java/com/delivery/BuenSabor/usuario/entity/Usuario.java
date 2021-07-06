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
	
	private String nombre;
	
	@Column(name = "usuario", unique = true)
	private String nombreUsuario;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	
	public Usuario() {
    }

	public Usuario(String nombreUsuario, String email, String password) {
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
	}
	
    public Usuario(String nombre, String nombreUsuario,  String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
	/*@Override
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
	}*/
	
	
}
