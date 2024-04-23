package com.projetointegrador.agendaclinica.resources;


import com.projetointegrador.agendaclinica.domain.Clinica;
import com.projetointegrador.agendaclinica.domain.dtos.ClinicaDTO;
import com.projetointegrador.agendaclinica.services.ClinicaService;
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
@RequestMapping(value = "/servicos")
public class servicoResource {

    @Autowired
    private ClinicaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicaDTO> findById(@PathVariable Integer id) {
        Clinica obj = service.findById(id);
        return ResponseEntity.ok(new ClinicaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> findAll() {
        List<Clinica> list = service.findAll();
        List<ClinicaDTO> listDTO = list.stream().map(obj -> new ClinicaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('PACIENTE')")
    @PostMapping
    public ResponseEntity<ClinicaDTO> create(@Valid @RequestBody ClinicaDTO objDTO) {
        Clinica newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClinicaDTO> update(@PathVariable Integer id, @Valid @RequestBody ClinicaDTO objDTO) {
        Clinica obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ClinicaDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClinicaDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
