package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Entity.Turno;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.OdontologoService;
import com.example.odontologia_spring_data.Service.PacienteService;
import com.example.odontologia_spring_data.Service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/turno")
public class TurnoController {

    private static final Logger logger= Logger.getLogger(OdontologoController.class);
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Operation(summary = "Lista todos los turnos de la base")
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        logger.info("Listando todos los turnos");
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @Operation(summary = "Devuelve un turno según la Id proporcionada")
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando turno por ID");
        Turno turno = turnoService.buscarPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        } else {
            logger.error("Error al buscar turno. Inexistente");
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Registra un turno en la base")
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        logger.info("Guardando turno");
        Paciente pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardar(turno)); //si el retorno es correcto , seria un 200
        }else{
            logger.error("Error al guardar turno");
            throw new ResourceNotFoundException("Paciente y/o Odontólogo no existe");
        }
    }

    @Operation(summary = "Borra un turno de la base según el id proporcionado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando turno");
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modifica los datos de un turno")
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable Long id, @RequestBody Turno turnoActualizado) {
        logger.info("Modificando turno");
        Turno turnoExistente = turnoService.buscarPorId(id);

        if (turnoExistente == null) {
            logger.info("Error al modificar turno. Inexistente");
            return ResponseEntity.notFound().build();
        }

        turnoExistente.setFecha(turnoActualizado.getFecha());
        turnoExistente.setOdontologo(turnoActualizado.getOdontologo());
        turnoExistente.setPaciente(turnoActualizado.getPaciente());

        Turno turnoGuardado = turnoService.actualizar(turnoExistente);
        return ResponseEntity.ok(turnoGuardado);
    }

}