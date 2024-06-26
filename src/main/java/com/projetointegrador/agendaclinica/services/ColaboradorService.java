package com.projetointegrador.agendaclinica.services;

import com.projetointegrador.agendaclinica.domain.Colaborador;
import com.projetointegrador.agendaclinica.domain.Pessoa;
import com.projetointegrador.agendaclinica.domain.dtos.ColaboradorDTO;
import com.projetointegrador.agendaclinica.repositories.ColaboradorRepository;
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
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Colaborador findById(Integer id){
        Optional<Colaborador> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoudException("Objeto não encontrado! Id: " + id));
    }

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Colaborador create(ColaboradorDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Colaborador newObj = new Colaborador(objDTO);
        return repository.save(newObj);
    }

    public Colaborador update(Integer id, @Valid ColaboradorDTO objDTO){
        objDTO.setId(id);
        Colaborador oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha())){
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        }
        validaPorCpfEEmail(objDTO);
        oldObj = new Colaborador(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Colaborador obj = findById(id);
//        if(obj.getAgendamentos().size() > 0){
//            throw new DataIntegrityViolationException("O colaborador tem agendamentos e não pode ser deletado!");
//        }
            repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ColaboradorDTO objDTO) {
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
