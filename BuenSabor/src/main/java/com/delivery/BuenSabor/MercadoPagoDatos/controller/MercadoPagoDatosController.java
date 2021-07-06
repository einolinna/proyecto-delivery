package com.delivery.BuenSabor.MercadoPagoDatos.controller;

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

import com.delivery.BuenSabor.DetalleFactura.entity.DetalleFactura;
import com.delivery.BuenSabor.Factura.entity.Factura;
import com.delivery.BuenSabor.MercadoPagoDatos.entiy.MercadoPagoDatos;
import com.delivery.BuenSabor.MercadoPagoDatos.service.MercadoPagoDatosServiceImpl;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/mpago")
public class MercadoPagoDatosController {

	
	@Autowired
	protected MercadoPagoDatosServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/all")
	public ResponseEntity<?> allMpagoDatos(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id){
		Optional<MercadoPagoDatos> f = service.findById(id);
		if(f.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(f.get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody MercadoPagoDatos datos, @PathVariable Long id) {
		Optional<MercadoPagoDatos> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		MercadoPagoDatos datosDb = o.get();
		datosDb.setFechaAprobacion(datos.getFechaAprobacion());
		datosDb.setFechaCreacion(datos.getFechaCreacion());
		datosDb.setFormaPago(datos.getFormaPago());
		datosDb.setMetodoPago(datos.getMetodoPago());
		datosDb.setPedido(datos.getPedido());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(datosDb));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody MercadoPagoDatos mPagoD) {
		MercadoPagoDatos mPD = service.save(mPagoD);
		return ResponseEntity.status(HttpStatus.CREATED).body(mPD);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAJERO')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarMpagoD(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	//El token tendría que pasarlo en los argumentos
	@PostMapping("/efectuarpago/{factura}")
	public ResponseEntity<?> realizarPago(@RequestBody Factura factura) throws MPException {
		MercadoPago.SDK.setAccessToken("TEST-3502556041132733-050214-ef47a9e5aa971c2965bc747986c19440-187659340");
		Preference preference = new Preference();
		Item item = new Item();
		for (DetalleFactura detalle : factura.getDetallesFacturas()) {
			if(detalle.getArticuloInsumo() == null) {
				item.setTitle(detalle.getArticuloMfact().getDenominacion())
				.setQuantity(detalle.getCantidad())
				.setUnitPrice((float)detalle.getArticuloMfact().getPrecioVenta())
				;
				preference.appendItem(item);
			} else {
				item.setTitle(detalle.getArticuloInsumo().getDenominacion())
				.setQuantity(detalle.getCantidad())
				.setUnitPrice((float) detalle.getArticuloInsumo().getPrecioVenta());
				preference.appendItem(item);
			}
			
		}
		preference.save();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(preference);
	}
}
