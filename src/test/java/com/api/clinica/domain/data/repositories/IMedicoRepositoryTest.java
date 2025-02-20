package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IMedicoRepositoryTest {

    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deberia retornar nulo cuando el medico se encuentre en consulta" +
            " con otro paciente en ese horario")
    void selectMedicoWithSpecialtyOnDateCaseOne() {
        // given
        var proximoLuner10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var patient= registerPatient();
        var medico= registerMedico();
        registerConsult(patient, medico, proximoLuner10H);

        // when
        var medicoLibre = medicoRepository.selectMedicoWithSpecialtyOnDate(Specialty.CARDIOLOGIA, proximoLuner10H);

        // then
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia retornar un medico cuando realice la consulta" +
            " en la base de datos en ese horario")
    void selectMedicoWithSpecialtyOnDateCaseTwo() {
        // given
        var proximoLuner10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico= registerMedico();
        // when
        var medicoLibre = medicoRepository.selectMedicoWithSpecialtyOnDate(Specialty.CARDIOLOGIA, proximoLuner10H);
        // then
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registerConsult(PatientEntity patient, MedicoEntity medico, LocalDateTime date) {
        var consult = new ConsultEntity(null,patient, medico, date, null);
        em.persist(consult);
    }

    private MedicoEntity registerMedico() {
        var medico = dataMedico();
        em.persist(medico);
        return medico;
    }

    private PatientEntity registerPatient() {
        var patient = dataPatient();
        em.persist(patient);
        return patient;
    }

    private PatientEntity dataPatient() {
        return new PatientEntity(
                null,
                "John Doe",
                "john.doe@example.com",
                "123456789",
                "555123456",
                true,
                LocalDate.of(1990, 1, 1),
                33,
                new AddressEntity(null, "Main Street", "Some City", "Some Country", 12, "")
        );

    }

    private MedicoEntity dataMedico() {
        return new MedicoEntity(
                null,
                "Anna Smith",
                "anna.smith@example.com",
                "555333111",
                "999888777",
                true,
                Specialty.CARDIOLOGIA,
                new AddressEntity(null, "Main Street", "Some City", "Some Country", 12, "")
        );
    }
}