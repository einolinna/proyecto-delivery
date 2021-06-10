package com.delivery.BuenSabor.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.BuenSabor.usuario.entity.Rol;
import com.delivery.BuenSabor.usuario.service.RolServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/rol")
public class RolController {

	@Autowired
	private RolServiceImpl service;
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearRol(@RequestBody Rol rol) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rol));
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}
}
