package com.api.clinica.controller;

import com.api.clinica.domain.data.entities.Specialty;
import com.api.clinica.domain.dto.DataDetailsConsult;
import com.api.clinica.domain.dto.DataScheduleConsult;
import com.api.clinica.domain.service.ConsultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<DataScheduleConsult> scheduleConsultJacksonTester;
    private JacksonTester<DataDetailsConsult> detailsConsultJacksonTester;
    @MockBean
    private ConsultService consultService;

    @Test
    @DisplayName("Deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void scheduleCaseOne() throws Exception {
        // given
        var response = mockMvc.perform(post("/v1/api/clinica/consult")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void scheduleCaseTwo() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var data = new DataDetailsConsult(null, "Andrés", "Juan", date);

        // when
        when(consultService.scheduleConsult(any())).thenReturn(data);

        // given
        var response = mockMvc.perform(post("/v1/api/clinica/consult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(scheduleConsultJacksonTester.write(new DataScheduleConsult(null, 1L,1L ,date, Specialty.CARDIOLOGIA)).getJson())
        ).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detailsConsultJacksonTester.write(data).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
  
}