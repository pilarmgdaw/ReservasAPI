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
    boolean existsByMesaIdAndFecha(Mesa mesaId, LocalDate fecha);
    List<Reserva> findByMesaIdAndFecha(Long mesaId, LocalDate fecha);
    List<Reserva> findByFecha(LocalDate fecha);
}