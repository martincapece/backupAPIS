package com.api.ecommerce.repository;

import com.api.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByUsuario(String usuario);
    boolean existsByMail(String mail);
    Optional<Cliente> findByUsuario(String usuario);
}