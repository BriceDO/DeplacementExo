package com.example.demo.repository;

import com.example.demo.model.DeplacementModel;
import com.example.demo.model.PatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class PatientRepository {

    @Autowired
    private WebClient webClient;

    // uri patient 8080
    @Value("${patient.uri}")
    private String URL;

    /**
     * Va retrouver un patient dans son microservice dédié
     * grâce à son id
     * @Param id
     * @Return mono patient
     */
    public Mono<PatientModel> getPatientById(String id){
        Mono<PatientModel> patient = webClient.get()
                .uri(URL + "/patients/" + id)
                .retrieve()
                .bodyToMono(PatientModel.class);
        return patient;
    }
}
