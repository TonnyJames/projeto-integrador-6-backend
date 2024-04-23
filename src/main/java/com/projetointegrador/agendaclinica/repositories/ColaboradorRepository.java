package com.projetointegrador.agendaclinica.repositories;

import com.projetointegrador.agendaclinica.domain.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
