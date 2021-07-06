package com.delivery.BuenSabor.cliente.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.BuenSabor.cliente.entity.Cliente;
import com.delivery.BuenSabor.cliente.service.ClienteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/cliente")
public class Clientecontroller {

	@Autowired
	protected ClienteServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/all")
	public ResponseEntity<?> allCliente(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id){
		Optional<Cliente> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	//Revisar seguridad para que un cliente no pueda podificar datos del administrador
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Optional<Cliente> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.setApellido(cliente.getApellido());
		clienteDb.setDomicilio(cliente.getDomicilio());
		clienteDb.setEmail(cliente.getEmail());
		clienteDb.setNombre(cliente.getNombre());
		clienteDb.setPedidos(cliente.getPedidos());
		clienteDb.setTelefono(cliente.getTelefono());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {
		Cliente clienteDb = service.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDb);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminaraAdmin(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
