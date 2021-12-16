package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeplacementModel {

    @Id
    private String id;
    private String idPatient;
    private String idInfirmier;
    private Date date;
    private float cout;

}
