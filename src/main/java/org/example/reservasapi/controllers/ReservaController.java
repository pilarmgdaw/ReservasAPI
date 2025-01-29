package org.example.reservasapi.controllers;

import org.example.reservasapi.DTO.ReservaDTO;
import org.example.reservasapi.entities.Reserva;
import org.example.reservasapi.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@Validated
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(reservaService.listarReservasPorFecha(fecha));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}