package com.example.demo.controller;

import com.example.demo.model.DeplacementModel;
import com.example.demo.model.PatientInfirmierDeplacementModel;
import com.example.demo.service.DeplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("deplacements")
public class DeplacementController {

    @Autowired
    DeplacementService service;

    /**
     * FindAll
     * @return liste de TOUS les deplacements
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DeplacementModel> findAll(){
        return service.findAll();
    }

    /**
     * FindById
     * @return un deplacement
     */
    @GetMapping("/finddeplacement/{id}")
    public Optional<DeplacementModel> findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    /**
     * FindAllNextByIdPatient
     * @Param l'id du patient
     * @Return liste des déplacements par patient à partir d'aujourd'hui
     */
    @GetMapping("/findallnextbypatient/{id}")
    public List<DeplacementModel> findAllByIdPatient(@PathVariable String id) {
        return service.FindAllByPatient(id);
    }

    /**
     * FindAllNextByIdInfirmier
     * @Param l'id de l'infirmier
     * @Return liste des déplacements par infirmier à partir d'aujourd'hui
     */
    @GetMapping("/findallnextbyinfirmier/{id}")
    public List<DeplacementModel> findAllByIdInfirmier(@PathVariable String id){
        return service.FindAllByInfirmier(id);
    }

    /**
     * FindNext
     * @return liste de TOUS les déplacements à partir d'aujourd'hui
     */
    @GetMapping("/findallnext")
    public List<DeplacementModel> findNext(){
        return service.findNext();
    }

    /**
     * FindOneDeplacement
     * @Param l'id du deplacement
     * @Return Un PatientInfirmierDeplacementModel avec le nom et prenom du patient et medecin associé
     */
    @GetMapping("/findOneDeplacement/{id}")
    public PatientInfirmierDeplacementModel findOneDeplacement(@PathVariable String id){
        return service.findOneDeplacement(id);
    }

    /**
     * Create
     */
    @PostMapping()
        public DeplacementModel create(@RequestBody DeplacementModel deplacement){
            return service.save(deplacement);
    }

    /**
     * Update
     */
    @PutMapping()
    public DeplacementModel update(@RequestBody DeplacementModel deplacement){
        return service.save(deplacement);
    }

    /**
     * Delete
     */
    @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable String id){
            service.delete(id);
            return ResponseEntity.ok("Deplacement Supprimé !");
    }
}
