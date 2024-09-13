package com.example.odontologia_spring_data.service;

import com.example.odontologia_spring_data.Entity.Domicilio;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente= new Paciente("jorgito","Pereyra","1111111", LocalDate.of(2024,9,10),"jorgito@jorgito.com",new Domicilio("Calle 1",12,"La Rioja","La Rioja"));
        Paciente pacienteGuardado= pacienteService.guardar(paciente);
        assertEquals("jorgito",pacienteGuardado.getNombre());
    }
    @Test
    @Order(2)
    public void buscarPaciente(){
        Long id=1L;
        Paciente pacienteBuscado= pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado.getId());
    }
    @Test
    @Order(3)
    public void actualizarPaciente(){
        Paciente pacienteBuscado= pacienteService.buscarPorId(1L);
        if(pacienteBuscado != null){
            pacienteBuscado.setApellido("Perez");
        }
        Paciente pacienteActualizado = pacienteService.actualizar(pacienteBuscado);
        assertEquals("Perez",pacienteActualizado.getApellido());
    }
    @Test
    @Order(4)
    public void listarTodos(){
        List<Paciente> pacientes= pacienteService.listarTodos();
        assertEquals(1,pacientes.size());
    }
    @Test
    @Order(5)
    public void eliminaPaciente(){
        pacienteService.eliminar(1L);
        Paciente pacienteEliminado= pacienteService.buscarPorId(1L);
        assertFalse(pacienteEliminado!= null);
    }
}
