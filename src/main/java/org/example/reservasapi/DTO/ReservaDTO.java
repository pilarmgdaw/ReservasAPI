package org.example.reservasapi.dto;

import lombok.Data;

@Data
public class ReservaDTO {
    private String nombre;
    private String email;
    private String fechaReserva;
    private String numeroMesa;
    // Otros campos necesarios
}