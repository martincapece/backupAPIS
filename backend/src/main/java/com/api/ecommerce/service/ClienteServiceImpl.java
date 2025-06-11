package com.api.ecommerce.service;

import com.api.ecommerce.model.Cliente;
import com.api.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        if (existeUsuario(cliente.getUsuario())) {
            throw new RuntimeException("El usuario ya existe");
        }
        if (existeMail(cliente.getMail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        // Aquí podrías agregar lógica para encriptar la contraseña
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteExistente.setNombre_completo(cliente.getNombre_completo());
        clienteExistente.setMail(cliente.getMail());
        clienteExistente.setUsuario(cliente.getUsuario());
        // Actualizar otros campos según necesidad
        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    
    public boolean existeUsuario(String usuario) {
        return clienteRepository.existsByUsuario(usuario);
    }

    
    public boolean existeMail(String mail) {
        return clienteRepository.existsByMail(mail);
    }
}