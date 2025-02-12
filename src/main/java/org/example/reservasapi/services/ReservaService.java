package org.example.reservasapi.services;

import org.example.reservasapi.DTO.ReservaDTO;
import org.example.reservasapi.entities.Reserva;

import org.example.reservasapi.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) {
        // Verificar disponibilidad de la mesa
        if (reservaRepository.existsByMesaIdAndFechaHora(reserva.getMesa(), reserva.getFechaHora())) {
            throw new IllegalArgumentException("La mesa no está disponible para la fecha y hora seleccionadas");
        }
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> listarReservasPorFecha(LocalDate fecha) {
        List<Reserva> listadoReservas = reservaRepository.findByFecha(fecha);
        List<ReservaDTO> listadoReservasDTO = new ArrayList<>();

        for (Reserva reserva : listadoReservas) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reserva.setId(reserva.getId());
            reserva.setFechaHora(reserva.getFechaHora());
            reserva.setNumeroPersonas(reserva.getNumeroPersonas());
            reserva.setCliente(reserva.getCliente());
            reserva.setMesa(reserva.getMesa());
            listadoReservasDTO.add(reservaDTO);
        }
        return listadoReservasDTO;
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }
    public boolean isMesaDisponible(Long mesaId, LocalDateTime fechaHora) {
        List<Reserva> reservas = reservaRepository.findByMesaIdAndFechaHora(mesaId, fechaHora);
        return reservas.isEmpty();
    }
}