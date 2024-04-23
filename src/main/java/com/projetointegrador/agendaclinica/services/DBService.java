package com.projetointegrador.agendaclinica.services;


import com.projetointegrador.agendaclinica.domain.Agendamento;
import com.projetointegrador.agendaclinica.domain.Paciente;
import com.projetointegrador.agendaclinica.domain.Clinica;
import com.projetointegrador.agendaclinica.domain.enums.Horarios;
import com.projetointegrador.agendaclinica.repositories.AgendamentosRepository;
import com.projetointegrador.agendaclinica.repositories.PacienteRepository;
import com.projetointegrador.agendaclinica.repositories.ColaboradorRepository;
import com.projetointegrador.agendaclinica.repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class DBService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private AgendamentosRepository agendamentosRepository;
    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){

//        Colaborador colab1 = new Colaborador(null, "Christine", "82831771099", "christine@mail.com", encoder.encode("123"));
//        colab1.addPerfil(Perfil.COLABORADOR);
//        Colaborador colab2 = new Colaborador(null, "Abraham", "86237320067", "abraham@mail.com", encoder.encode("123"));
//        colab2.addPerfil(Perfil.COLABORADOR);
//        Colaborador colab3 = new Colaborador(null, "Andre", "09814693022", "andre@mail.com", encoder.encode("123"));
//        colab3.addPerfil(Perfil.COLABORADOR);

        Paciente cli1 = new Paciente(null, "Jackeline dos Santos Soares", "75409273052", "jack@mail.com", encoder.encode("123"));
        Paciente cli2 = new Paciente(null, "Bruna Pereira de Souza", "36464790005", "bruna@mail.com", encoder.encode("123"));
        Paciente cli3 = new Paciente(null, "Tonny James P. Dos Reis", "75909714058", "tonny@mail.com", encoder.encode("123"));
        Paciente cli4 = new Paciente(null, "Roger Yukio Delvalle", "62076562028", "roger@mail.com", encoder.encode("123"));
        Paciente cli5 = new Paciente(null, "Luma Taynara Guimaraes Yoshida", "42006330004", "luma@mail.com", encoder.encode("123"));
        Paciente cli6 = new Paciente(null, "André Santos Isidoro", "71807965066", "andre@mail.com", encoder.encode("123"));

        Clinica clinica1 = new Clinica(null, Set.of(1, 2, 3), "Master Clinica", "53416478000163", "(11) 2764-6317", "masterclinica@mail.com", cli1);
        Clinica clinica2 = new Clinica(null, Set.of(3, 2, 1), "Health Care", "56462241000107", "(98) 3398-5480", "healthcare@mail.com", cli2);
        Clinica clinica3 = new Clinica(null, Set.of(4, 5, 6), "Clinica Popular", "07422607000109", "(21) 2986-4393", "clinicapopular@mail.com", cli3);
        Clinica clinica4 = new Clinica(null, Set.of(7, 8, 9), "Science Clinic", "16106001000155", "(84) 3548-5964", "scienceclinic@mail.com", cli4);

        Agendamento agend1 = new Agendamento(null, clinica4, "2022-12-28", Horarios.H3, "teste titulo", "Teste observação", cli3);
        Agendamento agend2 = new Agendamento(null, clinica1, "2022-12-11", Horarios.H2, "teste titulo", "Teste observação", cli2);
        Agendamento agend3 = new Agendamento(null, clinica2, "2022-12-12", Horarios.H1, "teste titulo", "Teste observação", cli1);
        Agendamento agend4 = new Agendamento(null, clinica3, "2022-12-05", Horarios.H3, "teste titulo", "Teste observação", cli4);
        Agendamento agend5 = new Agendamento(null, clinica2, "2022-12-05", Horarios.H3, "teste titulo", "Teste observação", cli5);
        Agendamento agend6 = new Agendamento(null, clinica4, "2022-12-05", Horarios.H3, "teste titulo", "Teste observação", cli6);

//        colaboradorRepository.saveAll(Arrays.asList(colab1, colab2, colab3));
        pacienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6));
        clinicaRepository.saveAll(Arrays.asList(clinica1, clinica2, clinica3, clinica4));
        agendamentosRepository.saveAll(Arrays.asList(agend1, agend2, agend3, agend4, agend5, agend6));
//
    }
}
