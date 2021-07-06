package com.delivery.BuenSabor.articuloManufacturado.controller;

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

import com.delivery.BuenSabor.articuloManufacturado.entity.ArticuloMfact;
import com.delivery.BuenSabor.articuloManufacturado.service.ArticuloMfactServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/articulomanufaturado")
public class articuloMfactController {

	@Autowired
	protected ArticuloMfactServiceImpl service;
	
	@GetMapping("/all")
	public ResponseEntity<?> AllArticulo() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id){
		Optional<ArticuloMfact> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ArticuloMfact articulo, @PathVariable Long id) {
		Optional<ArticuloMfact> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ArticuloMfact articuloDb = o.get();
		articuloDb.setArticuloMfactDetalle(articulo.getArticulosMfactDetalle());
		articuloDb.setDenominacion(articulo.getDenominacion());
		articuloDb.setImagen(articulo.getImagen());
		articuloDb.setPrecioVenta(articulo.getPrecioVenta());
		articuloDb.setRubroGeneral(articulo.getRubroGeneral());
		articuloDb.setTiempoEstimadoCoccion(articulo.getTiempoEstimadoCoccion());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(articuloDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody ArticuloMfact articuloMfact) {
		ArticuloMfact articuloDb = service.save(articuloMfact);
		return ResponseEntity.status(HttpStatus.CREATED).body(articuloDb);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COCINERO') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUno(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/like")
	public ResponseEntity<?> like(){
		return ResponseEntity.ok().body(service.findByLike());
	}
}
