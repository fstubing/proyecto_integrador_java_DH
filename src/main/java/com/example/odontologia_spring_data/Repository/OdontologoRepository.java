package com.example.odontologia_spring_data.Repository;
import com.example.odontologia_spring_data.Entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
}
