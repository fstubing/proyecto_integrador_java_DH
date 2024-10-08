package com.example.odontologia_spring_data.Security;

import com.example.odontologia_spring_data.Entity.*;
import com.example.odontologia_spring_data.Repository.UsuarioRepository;
import com.example.odontologia_spring_data.Repository.OdontologoRepository;
import com.example.odontologia_spring_data.Repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {
    private static final Logger logger= Logger.getLogger(DatosIniciales.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "digitalHouse";
        String passSinCifrar2 = "1234";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        String passCifrado2 = passwordEncoder.encode(passSinCifrar2);
        Usuario usuarioAInsertar = new Usuario("jorgito", "jorgitodh", "admin@admin.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        usuarioRepository.save(new Usuario("fernando", "ferdh", "fer@mail.com", passCifrado2, UsuarioRole.ROLE_ADMIN));
        logger.info("usuario cargado con exito");
        Odontologo odontologo = new Odontologo("mat1", "fernando", "stubing");
        odontologoRepository.save(odontologo);
        Paciente paciente= new Paciente("jorgito","Pereyra","1111111", LocalDate.of(2024,9,10),"jorgito@jorgito.com",new Domicilio("Calle 1",12,"La Rioja","La Rioja"));
        Paciente pacienteGuardado= pacienteRepository.save(paciente);
    }}


