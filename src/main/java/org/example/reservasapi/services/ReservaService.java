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
        if (reservaRepository.existsByMesaIdAndFecha(reserva.getMesa(), reserva.getFecha())) {
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
            reservaDTO.setId(reserva.getId());
            reservaDTO.setFechaReserva(reserva.getFecha());
            reservaDTO.setNumeroMesa(reserva.getMesa().getNumero());
            reservaDTO.setNombre(reserva.getCliente().getNombre());
            reservaDTO.setEmail(reserva.toString());
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
    public boolean isMesaDisponible(Long mesaId, LocalDate fecha) {
        List<Reserva> reservas = reservaRepository.findByMesaIdAndFecha(mesaId, fecha);
        return reservas.isEmpty();
    }
}