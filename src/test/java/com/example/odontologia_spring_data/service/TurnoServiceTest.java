package com.example.odontologia_spring_data.service;

import com.example.odontologia_spring_data.Entity.Domicilio;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Entity.Turno;
import com.example.odontologia_spring_data.Service.OdontologoService;
import com.example.odontologia_spring_data.Service.PacienteService;
import com.example.odontologia_spring_data.Service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarTurno() {
        Domicilio domicilio = new Domicilio("libertad", 1020, "Viña del Mar", "Viña del Mar");
        Paciente paciente = pacienteService.guardar(new Paciente("Alicia", "Silverstone", "111111-1", LocalDate.of(1997, 3, 22), "alicia.s@gmail.com", domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("mat-1423", "Agustin", "Parra"));
        Turno turno = new Turno(LocalDate.of(2024, 12, 22), odontologo, paciente);
        Turno turnoGuardado = turnoService.guardar(turno);
        assertEquals(1, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarTurno() {
        Long id = 1L;
        Turno turnoBuscado = turnoService.buscarPorId(id);
        assertTrue(turnoBuscado!= null);
    }

    @Test
    @Order(3)
    void actualizarTurno() {
        Domicilio domicilio = new Domicilio("Los Carrera", 404, "Quilpue", "Quilpue");
        Paciente paciente = pacienteService.guardar(new Paciente("Gal", "Gadoth", "22222-2", LocalDate.of(2023, 3, 15), "gal@gmail.com", domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("mat-987","Fernando", "Alvear"));
        Turno turno = new Turno(1L,LocalDate.of(2025, 3, 10), odontologo, paciente);
        turnoService.actualizar(turno);
        assertEquals("Gal", turnoService.buscarPorId(1L).getPaciente().getNombre());
    }

    @Test
    @Order(4)
    void buscarTodosLosTurnos() {
        List<Turno> listaTurnos = turnoService.listarTodos();
        assertEquals(1, listaTurnos.size());
    }

    @Test
    @Order(5)
    void eliminarTurno() {
        Long id = 1L;
        turnoService.eliminar(id);
        Turno turnoEliminado = turnoService.buscarPorId(1L);
        assertNull(turnoEliminado);
    }
}
