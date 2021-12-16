package com.example.demo.repository;

import com.example.demo.model.InfirmierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class InfirmierRepository {

    @Autowired
    private WebClient webClient;

    // uri infirmer 8081
    @Value("${infirmier.uri}")
    private String URL;

    /**
     * Va retrouver un infirmier dans son microservice dédié
     * grâce à son id
     * @Param id
     * @Return mono infirmier
     */
    public Mono<InfirmierModel> getInfirmierById(String id){
        Mono<InfirmierModel> infirmier = webClient.get()
                .uri(URL + "/infirmiers/" + id)
                .retrieve()
                .bodyToMono(InfirmierModel.class);
        return infirmier;
    }
}
