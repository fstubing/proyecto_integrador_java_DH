package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import java.util.List;
@Controller
@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private static final Logger logger= Logger.getLogger(OdontologoController.class);
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> listarTodos() {
        return odontologoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Odontologo odontologo = odontologoService.buscarPorId(id);
        logger.info("y q pasa");
        if (odontologo != null) {
            return ResponseEntity.ok(odontologo);
        } else {

            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @PostMapping
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @RequestBody Odontologo odontologoActualizado) {
        Odontologo odontologoExistente = odontologoService.buscarPorId(id);

        if (odontologoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        odontologoExistente.setNombre(odontologoActualizado.getNombre());
        odontologoExistente.setApellido(odontologoActualizado.getApellido());
        odontologoExistente.setMatricula(odontologoActualizado.getMatricula());

        Odontologo odontologoGuardado = odontologoService.actualizar(odontologoExistente);
        return ResponseEntity.ok(odontologoGuardado);
    }
}



