package org.example.reservasapi.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {
    private Long id;
    private String nombre;
    private String email;
    private LocalDate fechaReserva;
    private String numeroMesa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}