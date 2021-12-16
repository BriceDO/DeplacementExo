package com.example.demo.repository;

import com.example.demo.model.DeplacementModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeplacementRepository extends MongoRepository<DeplacementModel, String> {

    List<DeplacementModel> findAllByDateIsAfter(LocalDate date);
    List<DeplacementModel> findAllByIdPatientAndDateIsAfter(String id, LocalDate date);
    List<DeplacementModel> findAllByIdInfirmierAndDateIsAfter(String id, LocalDate date);

}
