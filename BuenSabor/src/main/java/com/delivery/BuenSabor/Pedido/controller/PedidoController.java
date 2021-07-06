package com.delivery.BuenSabor.Pedido.controller;

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

import com.delivery.BuenSabor.Pedido.entity.Pedido;
import com.delivery.BuenSabor.Pedido.service.PedidoServiceImpl;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/pedido")
public class PedidoController {
	
	@Autowired
	protected PedidoServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO') or hasRole('ROLE_COCINERO')")
	@GetMapping("/all")
	public ResponseEntity<?> allpedidos(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO') or hasRole('ROLE_COCINERO')")
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id){
		Optional<Pedido> p = service.findById(id);
		if(!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(p.get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO') or hasRole('ROLE_COCINERO')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Pedido pedido, @PathVariable Long id) {
		Optional<Pedido> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Pedido pedidoDb = o.get();
		pedidoDb.setCliente(pedido.getCliente());
		pedidoDb.setDetallesPedido(pedido.getDetallesPedido());
		pedidoDb.setDomicilio(pedido.getDomicilio());
		pedidoDb.setEstado(pedido.getEstado());
		pedidoDb.setFactura(pedido.getFactura());
		pedidoDb.setFecha(pedido.getFecha());
		pedidoDb.setHoraEstimadaFin(pedido.getHoraEstimadaFin());
		pedidoDb.setMercadoPagoDatos(pedido.getMercadoPagoDatos());
		
		//Validacion de retiro si es local aplica el 10% de descuento
		
		if(pedido.getTipoEnvio() == "Local") {
			pedidoDb.setTotal(pedido.getTotal() - (0.1 * pedido.getTotal()));
		}
		
		else {pedidoDb.setTotal(pedido.getTotal());}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pedidoDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO') or hasRole('ROLE_COCINERO')")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Pedido pedido) {
		Pedido facturaeDb = service.save(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(facturaeDb);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUno(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
