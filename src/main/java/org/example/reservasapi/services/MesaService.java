package org.example.reservasapi.services;

import org.example.reservasapi.entities.Mesa;
import org.example.reservasapi.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public Mesa crearMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa actualizarMesa(Long id, Mesa mesa) {
        mesa.setId(id);
        return mesaRepository.save(mesa);
    }

    public void eliminarMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }
}