package com.api.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nro_compra;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    @Column(name = "precio_final", nullable = false)
    private double precio_final;

    @Column(name = "medio_pago", nullable = false)
    private String medio_pago;
}