package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
@Controller
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente pacienteActualizado) {
        Paciente pacienteExistente = pacienteService.buscarPorId(id);

        if (pacienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        pacienteExistente.setNombre(pacienteActualizado.getNombre());
        pacienteExistente.setApellido(pacienteActualizado.getApellido());
        pacienteExistente.setCedula(pacienteActualizado.getCedula());
        pacienteExistente.setEmail(pacienteActualizado.getEmail());
        pacienteExistente.setFechaIngreso(pacienteActualizado.getFechaIngreso());

        if (pacienteExistente.getDomicilio() != null) {
            pacienteExistente.getDomicilio().setCalle(pacienteActualizado.getDomicilio().getCalle());
            pacienteExistente.getDomicilio().setNumero(pacienteActualizado.getDomicilio().getNumero());
            pacienteExistente.getDomicilio().setLocalidad(pacienteActualizado.getDomicilio().getLocalidad());
            pacienteExistente.getDomicilio().setProvincia(pacienteActualizado.getDomicilio().getProvincia());
        } else {
            pacienteExistente.setDomicilio(pacienteActualizado.getDomicilio());
        }

        Paciente pacienteGuardado = pacienteService.actualizar(pacienteExistente);
        return ResponseEntity.ok(pacienteGuardado);
    }
}