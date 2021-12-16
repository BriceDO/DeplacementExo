package com.example.demo.service;

import com.example.demo.model.DeplacementModel;
import com.example.demo.model.InfirmierModel;
import com.example.demo.model.PatientInfirmierDeplacementModel;
import com.example.demo.model.PatientModel;
import com.example.demo.repository.DeplacementRepository;
import com.example.demo.repository.InfirmierRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DeplacementService {

    @Autowired
    DeplacementRepository repository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    InfirmierRepository infirmierRepository;

    // findAll
    public List<DeplacementModel> findAll() {
        return repository.findAll();
    }

    // FindOne
    public Optional<DeplacementModel> findOne(String id){return repository.findById(id);}

    /**
     * FindNext()
     * Méthode permettant d’afficher uniquement les déplacements  à venir (donc à partir d'aujourd'hui)
     * @Return Liste de déplacements
     */
    public List<DeplacementModel> findNext(){
        return repository.findAllByDateIsAfter(LocalDate.now());
    }

    /**
     * FindAllByPatient(id)
     * Méthode permettant d’afficher tous les déplacements à venir (donc à partir d'aujourd'hui) d’un patient
     * @Param l'id du patient
     * @Return Liste de déplacements
     */
    public List<DeplacementModel> FindAllByPatient(String id){
        return repository.findAllByIdPatientAndDateIsAfter(id, LocalDate.now());
    }

    /**
     * FindAllByInfirmier(id)
     * Méthode permettant d’afficher tous les déplacements à venir (donc à partir d'aujourd'hui) d’un infirmier
     * @Param l'id de l'infirmier
     * @Return Liste des déplacements
     */
    public List<DeplacementModel> FindAllByInfirmier(String id){
        return repository.findAllByIdInfirmierAndDateIsAfter(id, LocalDate.now());
    }

    /**
     * FindOneDeplacement(id)
     * Méthode permettant d’afficher un déplacement avec nom prénom du patient et de l’infirmier
     * @Param String id du déplacement
     * @Return PatientInfirmierDeplacementModel
     */
    public PatientInfirmierDeplacementModel findOneDeplacement(String id){
        // Je recupère un deplacement
        Optional<DeplacementModel> deplacement = repository.findById(id);
        PatientInfirmierDeplacementModel patientInfirmierDeplacement = null;
        if (deplacement.isPresent()) {
            // Je recupère l'id du patient et de l'infirmier
            String idInfirmier = deplacement.get().getIdInfirmier();
            String idPatient = deplacement.get().getIdPatient();
            // J'appelle les microservices patient et infirmier
            Mono<InfirmierModel> infirmier = infirmierRepository.getInfirmierById(idInfirmier);
            Mono<PatientModel> patient = patientRepository.getPatientById(idPatient);
            // Je fusionne les trois données
            patientInfirmierDeplacement = new PatientInfirmierDeplacementModel(deplacement.get(), patient.block(), infirmier.block());

        }
        return patientInfirmierDeplacement;
    }

    // Create
    public DeplacementModel save(DeplacementModel deplacement){
        return repository.save(deplacement);
    }

    // Update
    public DeplacementModel update(DeplacementModel deplacement){
        return repository.save(deplacement);
    }

    // Delete
    public void delete(String id){
        repository.deleteById(id);
    }

}
