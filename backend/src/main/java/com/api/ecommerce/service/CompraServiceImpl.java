package com.api.ecommerce.service;

import com.api.ecommerce.model.Compra;
import com.api.ecommerce.model.Cliente;
import com.api.ecommerce.repository.CompraRepository;
import com.api.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {
    
    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    
    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository, ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
    }
    
    @Override
    public Compra realizarCompra(Compra compra) {
        if (compra.getId_cliente() == null) {
            throw new RuntimeException("Cliente no especificado");
        }
        Long idCliente = compra.getId_cliente().getId_cliente();
        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            
        compra.setFecha(java.time.LocalDateTime.now());
        return compraRepository.save(compra);
    }
    
    @Override
    public List<Compra> obtenerComprasPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return compraRepository.findByCliente(cliente);
    }
    
    @Override
    public Compra obtenerCompraPorId(Long id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }
    
    @Override
    public List<Compra> obtenerTodasLasCompras() {
        return compraRepository.findAll();
    }
    
    @Override
    public List<Compra> obtenerComprasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return compraRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
}