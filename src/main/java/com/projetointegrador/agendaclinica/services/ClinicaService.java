package com.projetointegrador.agendaclinica.services;

import com.projetointegrador.agendaclinica.domain.Paciente;
import com.projetointegrador.agendaclinica.domain.Clinica;
import com.projetointegrador.agendaclinica.domain.dtos.ClinicaDTO;
import com.projetointegrador.agendaclinica.repositories.ClinicaRepository;
import com.projetointegrador.agendaclinica.services.exceptions.DataIntegrityViolationException;
import com.projetointegrador.agendaclinica.services.exceptions.ObjectnotFoudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    //    @Autowired
//    private ColaboradorService colaboradorService;
    @Autowired
    private PacienteService pacienteService;

    public Clinica findById(Integer id) {
        Optional<Clinica> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoudException("Objeto não encontrado! Id: " + id));
    }

    public List<Clinica> findByAdmin(Integer id) throws Exception {
        try {
            List<Clinica> objLista = repository.findByAdmin(id);
            return objLista;
        } catch (Exception e) {
            throw new ObjectnotFoudException("Erro ao buscar lista de servico pelo id do usuario: " + id);
        }
    }

    public List<Clinica> findAll() {
        return repository.findAll();
    }

    public Clinica create(ClinicaDTO objDTO) {
        objDTO.setId(null);
//        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorNrInscEEmail(objDTO);
        Clinica newObj = newClinica(objDTO);
        return repository.save(newObj);
    }

    public Clinica update(Integer id, @Valid ClinicaDTO objDTO) {
        objDTO.setId(id);
        Clinica oldObj = findById(id);

//        if(!objDTO.getSenha().equals((oldObj))) {
//            objDTO.setSenha(encoder.encode(objDTO.getSenha()));
//        }
        validaPorNrInscEEmail(objDTO);
        oldObj = newClinica(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Clinica obj = findById(id);
//        if (obj.getAgendamentos().size() > 0) {
//            throw new DataIntegrityViolationException("O serviço ainda possui agendamentos futuros e não pode ser deletado");
//        }
        repository.deleteById(id);
    }


    private Clinica newClinica(ClinicaDTO obj) {

        Paciente admin = pacienteService.findById(obj.getAdmin());
        Clinica clinica = new Clinica();

        if (obj.getId() != null) {
            clinica.setId(obj.getId());
        }

        obj.getCategorias().forEach(clinica::addCategorias);
        clinica.setNmNegocio(obj.getNmNegocio());
        clinica.setNrInsc(obj.getNrInsc());
        clinica.setTelefone(obj.getTelefone());
        clinica.setEmail(obj.getEmail());
        clinica.setAdmin(admin);

        return clinica;
    }


    private void validaPorNrInscEEmail(ClinicaDTO objDTO) {
        Optional<Clinica> obj = repository.findByNrInsc(objDTO.getNrInsc());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Numero de inscrição já cadastrado");
        }
        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }
    }
}
