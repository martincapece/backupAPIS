package com.api.ecommerce.repository;

import com.api.ecommerce.model.Compra;
import com.api.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCliente(Cliente cliente);
    List<Compra> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}