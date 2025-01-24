package org.example.reservasapi.repositories;



import org.example.reservasapi.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}