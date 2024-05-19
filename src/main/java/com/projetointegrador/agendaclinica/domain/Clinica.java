package com.projetointegrador.agendaclinica.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetointegrador.agendaclinica.domain.enums.Categoria;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CATEGORIA")
    private Set<Integer> categorias;
    private String nmNegocio;

    @Column(unique = true)
    private String nrInsc; //cpf ou cnpj
    private String telefone;

    @Column(unique = true)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Paciente admin;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "clinica")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Clinica() {
        //vazio
    }

    public Clinica(Integer id, Set<Integer> categorias, String nmNegocio, String nrInsc, String telefone, String email, Paciente admin) {
        this.id = id;
        this.categorias = categorias;
        this.nmNegocio = nmNegocio;
        this.nrInsc = nrInsc;
        this.telefone = telefone;
        this.email = email;
        this.admin = admin;
    }

    //getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Categoria> getCategorias() {
        return categorias.stream().map(x -> Categoria.toEnum(x)).collect(Collectors.toSet());
    }

    public void addCategorias(Categoria categoria) {
        this.categorias.add(categoria.getCodigo());
    }

    public String getNmNegocio() {
        return nmNegocio;
    }

    public void setNmNegocio(String nmNegocio) {
        this.nmNegocio = nmNegocio;
    }

    public String getNrInsc() {
        return nrInsc;
    }

    public void setNrInsc(String nrInsc) {
        this.nrInsc = nrInsc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Paciente getAdmin() {
        return admin;
    }

    public void setAdmin(Paciente responsavel) {
        this.admin = responsavel;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clinica clinica = (Clinica) o;
        return Objects.equals(id, clinica.id) && Objects.equals(nmNegocio, clinica.nmNegocio) && Objects.equals(nrInsc, clinica.nrInsc) && Objects.equals(email, clinica.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nmNegocio, nrInsc, email);
    }
}
