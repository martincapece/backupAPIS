package com.api.ecommerce.service;

import java.time.LocalDate;
import java.util.List;
import com.api.ecommerce.model.Compra; // Add this import, adjust the package if needed

public interface CompraService {
    Compra realizarCompra(Compra compra);
    List<Compra> obtenerComprasPorCliente(Long clienteId);
    Compra obtenerCompraPorId(Long id);
    List<Compra> obtenerTodasLasCompras();
    List<Compra> obtenerComprasPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
}