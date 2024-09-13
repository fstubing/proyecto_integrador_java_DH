package com.example.odontologia_spring_data.service;

import com.example.odontologia_spring_data.Entity.Domicilio;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Service.OdontologoService;
import com.example.odontologia_spring_data.Service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("mat123", "Silvester", "Stalone");
        Odontologo odontologoGuardado= odontologoService.guardar(odontologo);
        assertEquals("mat123",odontologoGuardado.getMatricula());
    }
    @Test
    @Order(2)
    public void buscarOdontologo(){
        Long id=1L;
        Odontologo odontologoBuscado= odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.getId());
    }
    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Odontologo odontologoBuscado= odontologoService.buscarPorId(1L);
        if(odontologoBuscado != null){
            odontologoBuscado.setApellido("Perez");
        }
        Odontologo pacienteActualizado = odontologoService.actualizar(odontologoBuscado);
        assertEquals("Perez",pacienteActualizado.getApellido());
    }
    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> odontologo= odontologoService.listarTodos();
        assertEquals(2,odontologo.size());
    }
    @Test
    @Order(5)
    public void eliminaOdontologo(){
        odontologoService.eliminar(1L);
        Odontologo odontologoEliminado= odontologoService.buscarPorId(1L);
        assertFalse(odontologoEliminado!= null);
    }
}
