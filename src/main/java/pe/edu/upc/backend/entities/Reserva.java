package pe.edu.upc.backend.entities;

import jakarta.persistence.*;

santiago
import java.time.LocalDate;
@Entity
@Table(name = "reservas")

public class Reserva {
main

@Entity
@Table(name = "reservas")
public class Reserva {
        @Id
        @Column(name = "id_reservas")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_restaurantes", nullable = false)
        private Restaurante restaurante;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_usuarios", nullable = false)
        private User usuario;

        @Column(name = "fecha_reserva", nullable = false)
        private LocalDate fechaReserva;

        @Column(name = "num_personas")
        private Integer numPersonas;

        @Column(name = "estado")
        private Boolean estado; // p.ej. confirmado/cancelado

        @Column(name = "fecha_creacion")
        private LocalDate fechaCreacion;

        public Reserva() {}

        /* Getters/Setters */
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public Restaurante getRestaurante() { return restaurante; }
        public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }

        public User getUsuario() { return usuario; }
        public void setUsuario(User usuario) { this.usuario = usuario; }

        public LocalDate getFechaReserva() { return fechaReserva; }
        public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

        public Integer getNumPersonas() { return numPersonas; }
        public void setNumPersonas(Integer numPersonas) { this.numPersonas = numPersonas; }

        public Boolean getEstado() { return estado; }
        public void setEstado(Boolean estado) { this.estado = estado; }

        public LocalDate getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    }
