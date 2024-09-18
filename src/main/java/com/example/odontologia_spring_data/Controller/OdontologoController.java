package com.example.odontologia_spring_data.Controller;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Exception.ResourceNotFoundException;
import com.example.odontologia_spring_data.Service.OdontologoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import java.util.List;
@OpenAPIDefinition(info = @Info(
        title = "API CLINICA ODONTOLÓGICA FERNANDO STUBING",
        description = "Todas las definiciones de las rutas disponibles",
        version = "1.0.0"))
@Controller
@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private static final Logger logger= Logger.getLogger(OdontologoController.class);
    @Autowired
    private OdontologoService odontologoService;

    @Operation(summary = "Lista todos los odontólogos de la base")
    @ApiResponse(responseCode = "200",description = "Odontólogo creado")
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        logger.info("Listando todos los odontólogos");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @Operation(summary = "Devuelve un odontólogo según la Id proporcionada")
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

    @Operation(summary = "Registra un odontólogo en la base")
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        logger.info("Guardando odontólgo");
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        return ResponseEntity.ok(odontologoGuardado);
    }

    @Operation(summary = "Borra un odontólgo de la base según el id proporcionado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando odontólogo");
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modifica los datos de un odontólogo")
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



