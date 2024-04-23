package com.projetointegrador.agendaclinica.services;

import com.projetointegrador.agendaclinica.domain.Paciente;
import com.projetointegrador.agendaclinica.domain.Pessoa;
import com.projetointegrador.agendaclinica.domain.dtos.PacienteDTO;
import com.projetointegrador.agendaclinica.repositories.PacienteRepository;
import com.projetointegrador.agendaclinica.repositories.PessoaRepository;
import com.projetointegrador.agendaclinica.services.exceptions.DataIntegrityViolationException;
import com.projetointegrador.agendaclinica.services.exceptions.ObjectnotFoudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Paciente findById(Integer id){
        Optional<Paciente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoudException("Objeto não encontrado! Id: " + id));
    }

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public Paciente create(PacienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Paciente newObj = new Paciente(objDTO);
        return repository.save(newObj);
    }

    public Paciente update(Integer id, @Valid PacienteDTO objDTO){
        objDTO.setId(id);
        Paciente oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha())){
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        }
        validaPorCpfEEmail(objDTO);
        oldObj = new Paciente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Paciente obj = findById(id);
        if(obj.getAgendamentos().size() > 0){
            throw new DataIntegrityViolationException("O PACIENTE tem agendamentos e não pode ser deletado!");
        }
            repository.deleteById(id);
    }

    private void validaPorCpfEEmail(PacienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Cpf Já cadastrado");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
            if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
                throw new DataIntegrityViolationException("E-mail já cadastrado");
            }
    }


}
