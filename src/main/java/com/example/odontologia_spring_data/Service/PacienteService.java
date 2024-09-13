package com.example.odontologia_spring_data.Service;


import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
