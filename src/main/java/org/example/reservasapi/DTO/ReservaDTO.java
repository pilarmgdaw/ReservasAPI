package org.example.reservasapi.DTO;

import lombok.Data;

@Data
public class ReservaDTO {
    private String nombre;
    private String email;
    private String fechaReserva;
    private String numeroMesa;
    
}