package com.api.clinica;

import com.api.clinica.utils.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {

    public static void main(String[] args) {
        EnvLoader.loadEnv();
        SpringApplication.run(ClinicaApplication.class, args);
    }

}
