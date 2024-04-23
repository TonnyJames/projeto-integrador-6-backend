package com.projetointegrador.agendaclinica.domain;

import com.projetointegrador.agendaclinica.domain.dtos.ColaboradorDTO;
import com.projetointegrador.agendaclinica.domain.enums.Perfil;

import javax.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class Colaborador extends Pessoa{

    private static final long serialVersionUID = 1L;

//    @JsonIgnore
//    @OneToMany(mappedBy = "colaborador")
//    private List<Agendamento> agendamentos = new ArrayList<>();

    public Colaborador() {
        super();
        addPerfil(Perfil.PACIENTE);
    }

    public Colaborador(ColaboradorDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public Colaborador(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.PACIENTE);
    }

//    public List<Agendamento> getAgendamentos() {
//        return agendamentos;
//    }
//
//    public void setAgendamentos(List<Agendamento> agendamentos) {
//        this.agendamentos = agendamentos;
//    }
}
