package com.api.ecommerce.service;

import com.api.ecommerce.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    List<Cliente> obtenerTodosLosClientes();
    Cliente obtenerClientePorId(Long id);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}