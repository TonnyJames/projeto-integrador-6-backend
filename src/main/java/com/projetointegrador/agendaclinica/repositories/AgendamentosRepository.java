package com.projetointegrador.agendaclinica.repositories;

import com.projetointegrador.agendaclinica.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentosRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findAgendamentoByPaciente_Cpf(String cpf);
}
