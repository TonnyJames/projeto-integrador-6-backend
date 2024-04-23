package com.projetointegrador.agendaclinica.services;

import com.projetointegrador.agendaclinica.domain.Agendamento;
import com.projetointegrador.agendaclinica.domain.Paciente;
import com.projetointegrador.agendaclinica.domain.Clinica;
import com.projetointegrador.agendaclinica.domain.dtos.AgendamentoDTO;
import com.projetointegrador.agendaclinica.domain.enums.Horarios;
import com.projetointegrador.agendaclinica.repositories.AgendamentosRepository;
import com.projetointegrador.agendaclinica.services.exceptions.ObjectnotFoudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentosRepository repository;
    @Autowired
    private ColaboradorService colaboradorService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ClinicaService clinicaService;

    public Agendamento findById(Integer id) {
        Optional<Agendamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoudException("Objeto Não encontrado " + id));
    }

    public List<Agendamento> findAgendamentoByCpfPaciente(String cpf) {
        try {
            List<Agendamento> objLista = repository.findAgendamentoByPaciente_Cpf(cpf);
            return objLista;
        } catch (Exception e) {
            throw new ObjectnotFoudException("Nenhum agendamento encontrado para o cpf: " + cpf + " em nossa base de dados.");
        }
    }

    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    public Agendamento create(AgendamentoDTO objDTO) {
        return repository.save(newAgendamento(objDTO));
    }

    public Agendamento update(Integer id, AgendamentoDTO objDTO) {
        objDTO.setId(id);
        Agendamento oldObj = findById(id);
        oldObj = newAgendamento(objDTO);
        return repository.save(oldObj);
    }


    private Agendamento newAgendamento(AgendamentoDTO obj) {
//        Colaborador colaborador = colaboradorService.findById(obj.getColaborador());
        Paciente paciente = pacienteService.findById(obj.getPaciente());
        Clinica clinica = clinicaService.findById(obj.getServico());

        Agendamento agendamento = new Agendamento();
        if (obj.getId() != null) {
            agendamento.setId(obj.getId());
        }

//        if (obj.getStatus().equals(2)){
//          agendamento.setDataAgendada(LocalDate.now());
//    }
        agendamento.setDataAgendada(LocalDate.parse(obj.getDataAgendada()));
        agendamento.setHoraAgendada(Horarios.toEnum(obj.getHoraAgendada()));
        agendamento.setServico(clinica);
//        agendamento.setColaborador(colaborador);
        agendamento.setPaciente(paciente);
        agendamento.setTitulo(obj.getTitulo());
//        agendamento.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
//        agendamento.setStatus(Status.toEnum(obj.getStatus()));
        agendamento.setObservacoes(obj.getObservacoes());
        return agendamento;
    }


}
