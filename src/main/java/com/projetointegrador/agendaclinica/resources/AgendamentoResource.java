package com.projetointegrador.agendaclinica.resources;

import com.projetointegrador.agendaclinica.domain.Agendamento;
import com.projetointegrador.agendaclinica.domain.dtos.AgendamentoDTO;
import com.projetointegrador.agendaclinica.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoResource {

    @Autowired
    private AgendamentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> findById(@PathVariable Integer id) {
        Agendamento obj = service.findById(id);
        return ResponseEntity.ok().body(new AgendamentoDTO(obj));
    }

//    @CrossOrigin(origins = "*")
    @GetMapping(value = "cpf/{cpf}"/*, produces = "application/json"*/)
    public ResponseEntity<List<AgendamentoDTO>> findAgendamentoByCpf(@PathVariable String cpf) {
        List<Agendamento> objLista = service.findAgendamentoByCpfPaciente(cpf);
        List<AgendamentoDTO> objListaDTO = objLista.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(objListaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> findAll(){
        List<Agendamento> list = service.findAll();
        List<AgendamentoDTO> listDTO = list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> create(@Valid @RequestBody AgendamentoDTO objDTO){
        Agendamento obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody AgendamentoDTO objDTO){
        Agendamento newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new AgendamentoDTO(newObj));
    }

}
