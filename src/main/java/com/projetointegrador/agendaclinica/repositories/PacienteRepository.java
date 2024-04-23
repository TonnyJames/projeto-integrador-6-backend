package com.projetointegrador.agendaclinica.repositories;

import com.projetointegrador.agendaclinica.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
