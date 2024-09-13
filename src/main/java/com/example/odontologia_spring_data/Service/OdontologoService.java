package com.example.odontologia_spring_data.Service;

import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo buscarPorId(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}