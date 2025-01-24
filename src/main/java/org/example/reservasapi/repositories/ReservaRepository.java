package org.example.reservasapi.repositories;

import org.example.reservasapi.entities.Mesa;
import org.example.reservasapi.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByMesaAndFechaHora(Mesa mesa, LocalDateTime fechaHora);
}