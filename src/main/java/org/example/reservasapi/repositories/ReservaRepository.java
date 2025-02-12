package org.example.reservasapi.repositories;

import org.example.reservasapi.entities.Mesa;
import org.example.reservasapi.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByMesaIdAndFechaHora(Mesa mesaId, LocalDateTime fechaHora);
    List<Reserva> findByMesaIdAndFechaHora(Long mesaId, LocalDateTime fechaHora);
    List<Reserva> findByFecha(LocalDate fecha);
}