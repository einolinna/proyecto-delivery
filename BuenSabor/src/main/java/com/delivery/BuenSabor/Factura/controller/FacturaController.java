package com.delivery.BuenSabor.Factura.controller;

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

import com.delivery.BuenSabor.Factura.entity.Factura;
import com.delivery.BuenSabor.Factura.service.FacturaServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/factura")
public class FacturaController {

	@Autowired
	protected FacturaServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/all")
	public ResponseEntity<?> allFacturas(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/{numero}")
	public ResponseEntity<?> byId(@PathVariable Integer numero){
		Optional<Factura> o = service.findByNumero(numero);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@PutMapping("/{numero}")
	public ResponseEntity<?> update(@RequestBody Factura factura, @PathVariable Integer numero) {
		Optional<Factura> o = service.findByNumero(numero);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Factura facturaDb = o.get();
		facturaDb.setDetallesFacturas(factura.getDetallesFacturas());
		facturaDb.setFecha(factura.getFecha());
		facturaDb.setFormaPago(factura.getFormaPago());
		facturaDb.setMontoDescuento(factura.getMontoDescuento());
		facturaDb.setNumTarjeta(factura.getNumTarjeta());
		facturaDb.setPedido(factura.getPedido());
		facturaDb.setTotalCosto(factura.getTotalCosto());
		facturaDb.setTotalVenta(factura.getTotalVenta());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(facturaDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Factura cliente) {
		Factura facturaeDb = service.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(facturaeDb);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> eliminarUna(@PathVariable Integer numero) {
		service.deleteByNumero(numero);
		return ResponseEntity.noContent().build();
	}
}
