package com.projetointegrador.agendaclinica.resources;

import com.projetointegrador.agendaclinica.domain.Colaborador;
import com.projetointegrador.agendaclinica.domain.dtos.ColaboradorDTO;
import com.projetointegrador.agendaclinica.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/colaboradores")
public class ColaboradorResource {

    //localhost:8080/colaboradores

    @Autowired
    private ColaboradorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ColaboradorDTO> findById(@PathVariable Integer id){
        Colaborador obj = service.findById(id);
        return ResponseEntity.ok(new ColaboradorDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> findAll(){
        List<Colaborador> list = service.findAll();
        List<ColaboradorDTO> listDTO = list.stream().map(obj -> new ColaboradorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ColaboradorDTO> create(@Valid @RequestBody ColaboradorDTO objDTO){
        Colaborador newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ColaboradorDTO> update(@PathVariable Integer id, @Valid @RequestBody ColaboradorDTO objDTO){
        Colaborador obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ColaboradorDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ColaboradorDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
