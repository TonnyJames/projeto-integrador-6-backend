package com.projetointegrador.agendaclinica.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetointegrador.agendaclinica.domain.Colaborador;
import com.projetointegrador.agendaclinica.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ColaboradorDTO implements Serializable {

    private static final Long serialVersionUID = 1l;

    protected Integer id;


    @NotNull(message = "O campo NOME é requerido")
    protected String nome;
    @NotNull(message = "O campo CPF é requerido")
    protected String cpf;
    @NotNull(message = "O campo E-MAIL é requerido")
    protected String email;
    @NotNull(message = "O campo SENHA é requerido")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ColaboradorDTO() {
        //vazio
        addPerfis(Perfil.PACIENTE);
    }

    public ColaboradorDTO(Colaborador obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfis(Perfil.PACIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}