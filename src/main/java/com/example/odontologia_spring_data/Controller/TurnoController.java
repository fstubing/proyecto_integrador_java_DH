package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Entity.Turno;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.OdontologoService;
import com.example.odontologia_spring_data.Service.PacienteService;
import com.example.odontologia_spring_data.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Turno> listarTodos() {
        return turnoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        Turno turno = turnoService.buscarPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Paciente pacienteBuscado= pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardar(turno)); //si el retorno es correcto , seria un 200
        }else{
            throw new ResourceNotFoundException("Paciente y/o Odontólogo no existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable Long id, @RequestBody Turno turnoActualizado) {
        Turno turnoExistente = turnoService.buscarPorId(id);

        if (turnoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        turnoExistente.setFecha(turnoActualizado.getFecha());
        turnoExistente.setOdontologo(turnoActualizado.getOdontologo());
        turnoExistente.setPaciente(turnoActualizado.getPaciente());

        Turno turnoGuardado = turnoService.actualizar(turnoExistente);
        return ResponseEntity.ok(turnoGuardado);
    }

}