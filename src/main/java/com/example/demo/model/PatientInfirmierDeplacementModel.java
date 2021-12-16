package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfirmierDeplacementModel {

    private DeplacementModel deplacement;
    private PatientModel patient;
    private InfirmierModel infirmier;
}
