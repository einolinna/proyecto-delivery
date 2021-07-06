package com.delivery.BuenSabor.ArticuloInsumo.controller;

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

import com.delivery.BuenSabor.ArticuloInsumo.entity.ArticuloInsumo;
import com.delivery.BuenSabor.ArticuloInsumo.service.ArticuloInsumoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/articuloinsumo")
public class ArticuloInsumoController {

	@Autowired
	protected ArticuloInsumoServiceImpl service;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@GetMapping("/all")
	public ResponseEntity<?> allInsumo() {
		return ResponseEntity.ok().body(service.finAll());
	}

	@GetMapping("/cliente")
	public ResponseEntity<?> allInsumoCliente() {
		return ResponseEntity.ok().body(service.findByInsumo());
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> byIdCliente(@PathVariable Long id) {
		Optional<ArticuloInsumo> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (o.get().getEsInsumo() == true) {
			return ResponseEntity.ok(o.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id) {
		Optional<ArticuloInsumo> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ArticuloInsumo articuloInsumo, @PathVariable Long id) {
		Optional<ArticuloInsumo> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ArticuloInsumo articuloDb = o.get();
		articuloDb.setDenominacion(articuloInsumo.getDenominacion());
		articuloDb.setEsInsumo(articuloInsumo.getEsInsumo());
		articuloDb.setPrecioCompra(articuloInsumo.getPrecioCompra());
		articuloDb.setPrecioVenta(articuloInsumo.getPrecioVenta());
		articuloDb.setRubroArticulo(articuloInsumo.getRubroArticulo());
		articuloDb.setStockActual(articuloInsumo.getStockActual());
		articuloDb.setStockMinimo(articuloInsumo.getStockMinimo());
		articuloDb.setUnidadMedida(articuloInsumo.getUnidadMedida());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(articuloDb));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody ArticuloInsumo articuloInsumo) {
		ArticuloInsumo articuloDb = service.save(articuloInsumo);
		return ResponseEntity.status(HttpStatus.CREATED).body(articuloDb);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUno(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/like")
	public ResponseEntity<?> like() {
		return ResponseEntity.ok().body(service.findByLike());
	}
}
