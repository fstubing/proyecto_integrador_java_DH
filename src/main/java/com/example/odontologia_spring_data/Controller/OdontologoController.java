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
    public ResponseEntity<List<Odontologo>> listarTodos() {
        logger.info("Listando todos los odontólogos");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        logger.info("Buscando odontólogo por ID");
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null) {
            return ResponseEntity.ok(odontologo);
        } else {
            logger.error("Error al buscar odontólogo por ID");
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        logger.info("Guardando odontólgo");
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        return ResponseEntity.ok(odontologoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando odontólogo");
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @RequestBody Odontologo odontologoActualizado) {
        logger.info("Modificando odontólogo");
        Odontologo odontologoExistente = odontologoService.buscarPorId(id);

        if (odontologoExistente == null) {
            logger.error("Error al modificar odontólogo. Inexistente");
            return ResponseEntity.notFound().build();
        }

        odontologoExistente.setNombre(odontologoActualizado.getNombre());
        odontologoExistente.setApellido(odontologoActualizado.getApellido());
        odontologoExistente.setMatricula(odontologoActualizado.getMatricula());

        Odontologo odontologoGuardado = odontologoService.actualizar(odontologoExistente);
        return ResponseEntity.ok(odontologoGuardado);
    }
}



