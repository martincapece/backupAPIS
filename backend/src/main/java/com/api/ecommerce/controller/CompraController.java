package com.api.ecommerce.controller;

import com.api.ecommerce.model.Compra;
import com.api.ecommerce.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    
    private final CompraService compraService;
    
    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    
    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = compraService.realizarCompra(compra);
        return new ResponseEntity<>(nuevaCompra, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Compra>> obtenerTodasLasCompras() {
        List<Compra> compras = compraService.obtenerTodasLasCompras();
        return ResponseEntity.ok(compras);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
        Compra compra = compraService.obtenerCompraPorId(id);
        return ResponseEntity.ok(compra);
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Compra>> obtenerComprasPorCliente(@PathVariable Long clienteId) {
        List<Compra> compras = compraService.obtenerComprasPorCliente(clienteId);
        return ResponseEntity.ok(compras);
    }
    
    @GetMapping("/fecha")
    public ResponseEntity<List<Compra>> obtenerComprasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<Compra> compras = compraService.obtenerComprasPorFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(compras);
    }
}