package com.projetointegrador.agendaclinica.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetointegrador.agendaclinica.domain.enums.Categoria;
import com.projetointegrador.agendaclinica.domain.enums.Horarios;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_criação")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAgendada;

    private Horarios horaAgendada;

    //    private Prioridade prioridade;
//    private Status status;
//    private String titulo;

    @CollectionTable(name = "CATEGORIA")
    private Categoria especialidade;

    private String observacoes;

//    @ManyToOne
//    @JoinColumn(name = "colaborador_id")
//    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    public Agendamento() {
        super();
    }

    public Agendamento(Integer id, Clinica clinica, String dataAgendada, Horarios horaAgendada, Categoria especialidade, String observacoes, Paciente paciente) {
        this.id = id;
        this.clinica = clinica;
        this.dataAgendada = LocalDate.parse(dataAgendada);
        this.horaAgendada = horaAgendada;
//        this.prioridade = prioridade;
//        this.status = status;
//        this.titulo = titulo;
        this.especialidade = especialidade;
        this.observacoes = observacoes;
//        this.colaborador = colaborador;
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public Horarios getHoraAgendada() {
        return horaAgendada;
    }

    public void setHoraAgendada(Horarios horaAgendada) {
        this.horaAgendada = horaAgendada;
    }

//    public Prioridade getPrioridade() {
//        return prioridade;
//    }
//
//    public void setPrioridade(Prioridade prioridade) {
//        this.prioridade = prioridade;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }


    public Categoria getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Categoria especialidade) {
        this.especialidade = especialidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

//    public Colaborador getColaborador() {
//        return colaborador;
//    }
//
//    public void setColaborador(Colaborador colaborador) {
//        this.colaborador = colaborador;
//    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Clinica getServico() {
        return clinica;
    }

    public void setServico(Clinica clinica) {
        this.clinica = clinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
