package com.delivery.BuenSabor.domicilio.controller;

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

import com.delivery.BuenSabor.domicilio.entity.Domicilio;
import com.delivery.BuenSabor.domicilio.service.DomicilioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(name = "/api/v1/domicilio")
public class DomicilioController {

	@Autowired
	protected DomicilioServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/all")
	public ResponseEntity<?> allDomicilio() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id) {
		Optional<Domicilio> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Domicilio domicilio, @PathVariable Long id)  {
		Optional<Domicilio> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Domicilio domicilioDb = o.get();
		domicilioDb.setLocalidad(domicilio.getLocalidad());
		domicilioDb.setCalle(domicilio.getCalle());
		domicilioDb.setNumero(domicilio.getNumero());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(domicilioDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Domicilio domicilio) {
		Domicilio domicilioDb = service.save(domicilio);
		return ResponseEntity.status(HttpStatus.CREATED).body(domicilioDb);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUno(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
