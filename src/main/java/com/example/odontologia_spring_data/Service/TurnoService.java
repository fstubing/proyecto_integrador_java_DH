package com.example.odontologia_spring_data.Service;

import com.example.odontologia_spring_data.Entity.Turno;
import com.example.odontologia_spring_data.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    public Turno buscarPorId(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    public Turno guardar(Turno turno) { return turnoRepository.save(turno); }
    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
}
