package com.projetointegrador.agendaclinica.services;


import com.projetointegrador.agendaclinica.domain.Agendamento;
import com.projetointegrador.agendaclinica.domain.Paciente;
import com.projetointegrador.agendaclinica.domain.Clinica;
import com.projetointegrador.agendaclinica.domain.enums.Categoria;
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

        Paciente paciente1 = new Paciente(null, "Jackeline dos Santos Soares", "75409273052", "jack@mail.com", encoder.encode("123"));
        Paciente paciente2 = new Paciente(null, "Bruna Pereira de Souza", "36464790005", "bruna@mail.com", encoder.encode("123"));
        Paciente paciente3 = new Paciente(null, "Tonny James P. Dos Reis", "75909714058", "tonny@mail.com", encoder.encode("123"));
        Paciente paciente4 = new Paciente(null, "Roger Yukio Delvalle", "62076562028", "roger@mail.com", encoder.encode("123"));
        Paciente paciente5 = new Paciente(null, "Luma Taynara Guimaraes Yoshida", "42006330004", "luma@mail.com", encoder.encode("123"));
        Paciente paciente6 = new Paciente(null, "André Santos Isidoro", "71807965066", "andre@mail.com", encoder.encode("123"));

        Clinica clinica1 = new Clinica(null, Set.of(1, 2, 3), "Master Clinica", "53416478000163", "(11) 2764-6317", "masterclinica@mail.com", paciente1);
        Clinica clinica2 = new Clinica(null, Set.of(3, 2, 1), "Health Care", "56462241000107", "(98) 3398-5480", "healthcare@mail.com", paciente2);
        Clinica clinica3 = new Clinica(null, Set.of(4, 5, 6), "Clinica Popular", "07422607000109", "(21) 2986-4393", "clinicapopular@mail.com", paciente3);
        Clinica clinica4 = new Clinica(null, Set.of(7, 8, 9), "Science Clinic", "16106001000155", "(84) 3548-5964", "scienceclinic@mail.com", paciente4);
        Clinica clinica5 = new Clinica(null, Set.of(7), "Clinica de Recuperação", "16106001000156", "(11) 2458-3158", "clinicaderecuperacao@mail.com", paciente4);
        Clinica clinica6 = new Clinica(null, Set.of(9), "Odontologia Especializada", "16106001000157", "(11) 2243-6798", "odontoclinica@mail.com", paciente4);
        Clinica clinica7 = new Clinica(null, Set.of(0, 1, 2, 3, 6, 8, 9, 10), "Centro Médico Com Vida ", "16106001000158", "(11) 2557-8614", "comvida@mail.com", paciente4);
        Clinica clinica8 = new Clinica(null, Set.of(5), "Consultório Dr. Juliana", "16106001000159", "(11) 2352-4177", "consultoriojuliana@mail.com", paciente4);
        Clinica clinica9 = new Clinica(null, Set.of(6), "Clínica MM Select", "16106001000110", "(11) 6816-2141", "mmselect@mail.com", paciente4);
        Clinica clinica11 = new Clinica(null, Set.of(1, 6), "Amor Saúde", "16106001000111", "(11) 6269-7523", "amorsaude@mail.com", paciente4);
        Clinica clinica12 = new Clinica(null, Set.of(11), "Sorriso", "16106001000112", "(11) 6083-7857", "sorrisoclinica@mail.com", paciente4);
        Clinica clinica13 = new Clinica(null, Set.of(0, 1, 2, 3, 6, 8, 9, 10), "CEMID", "16106001000113", "(11) 4225-3096", "cemid@mail.com", paciente4);
        Clinica clinica14 = new Clinica(null, Set.of(4), "Clinoca Dr. Paolo", "16106001000114", "(11) 2521-8093", "clinicapaolo@mail.com", paciente4);
        Clinica clinica15 = new Clinica(null, Set.of(7), "Psicóloga Joice Abreu", "16106001000115", "(11) 2558-6473", "psicjoice@mail.com", paciente4);

        Agendamento agend1 = new Agendamento(null, clinica4, "2022-12-28", Horarios.H3, Categoria.CLINICO, "Teste observação", paciente3);
        Agendamento agend2 = new Agendamento(null, clinica1, "2022-12-11", Horarios.H2, Categoria.DERMATOLOGISTA, "Teste observação", paciente2);
        Agendamento agend3 = new Agendamento(null, clinica2, "2022-12-12", Horarios.H1, Categoria.GERIATRA, "Teste observação", paciente1);
        Agendamento agend4 = new Agendamento(null, clinica3, "2022-12-05", Horarios.H3, Categoria.ONCOLOGISTA, "Teste observação", paciente4);
        Agendamento agend5 = new Agendamento(null, clinica2, "2022-12-05", Horarios.H3, Categoria.DERMATOLOGISTA, "Teste observação", paciente5);
        Agendamento agend6 = new Agendamento(null, clinica4, "2022-12-05", Horarios.H3, Categoria.OTORRINOLARINGOLOGISTA, "Teste observação", paciente6);

//        colaboradorRepository.saveAll(Arrays.asList(colab1, colab2, colab3));
        pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2, paciente3, paciente4, paciente5, paciente6));
        clinicaRepository.saveAll(Arrays.asList(clinica1, clinica2, clinica3, clinica4, clinica5, clinica6, clinica7, clinica8, clinica9, clinica11, clinica12, clinica13, clinica14, clinica15));
        agendamentosRepository.saveAll(Arrays.asList(agend1, agend2, agend3, agend4, agend5, agend6));
//
    }
}
