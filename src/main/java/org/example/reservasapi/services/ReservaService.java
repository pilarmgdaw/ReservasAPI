package org.example.reservasapi.services;

import org.example.reservasapi.dto.ReservaDTO;
import org.example.reservasapi.entities.Reserva;
import org.example.reservasapi.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) {
        // Verificar disponibilidad de la mesa
        if (reservaRepository.existsByMesaAndFechaHora(reserva.getMesa(), reserva.getFechaHora())) {
            throw new IllegalArgumentException("La mesa no está disponible para la fecha y hora seleccionadas");
        }
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaDTO> listarReservasPorFecha(LocalDate fecha) {
        // Lógica para listar reservas por fecha
        return null;
    }
}