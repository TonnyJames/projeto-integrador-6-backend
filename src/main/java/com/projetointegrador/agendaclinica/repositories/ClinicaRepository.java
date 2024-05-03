package com.projetointegrador.agendaclinica.repositories;

import com.projetointegrador.agendaclinica.domain.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    List<Clinica> findByAdmin(Integer AdminID);
    Optional<Clinica> findByNrInsc(String nrInsc);
    Optional<Clinica> findByEmail(String email);

    Optional<List<Clinica>> findByCategorias(Integer categoria);
}
