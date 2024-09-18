package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.Logger;
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

    private static final Logger logger= Logger.getLogger(OdontologoController.class);
    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Lista todos los pacientes de la base")
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        logger.info("Listando todos los pacientes");
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @Operation(summary = "Devuelve un paciente según la Id proporcionada")
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Buscando paciente por ID");
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            logger.error("Paciente no encontrado");
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @Operation(summary = "Registra un paciente en la base")
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        logger.info("Guardando paciente");
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @Operation(summary = "Borra un paciente de la base según el id proporcionado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando paciente");
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modifica los datos de un paciente")
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente pacienteActualizado) {
        logger.info("Modificando paciente");
        Paciente pacienteExistente = pacienteService.buscarPorId(id);

        if (pacienteExistente == null) {
            logger.error("Error al modificar paciente. Inexistente");
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