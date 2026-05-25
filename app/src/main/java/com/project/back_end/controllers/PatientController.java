package com.project_back_end.controllers;

import com.project_back_end.models.Patient;
import com.project_back_end.services.ServiceClass;
import com.project_back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        try {
            Patient savedPatient = serviceClass.savePatient(patient);
            return ResponseEntity.ok(savedPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering patient");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestBody Patient patient) {
        try {
            Patient existingPatient = serviceClass.getPatientByEmail(patient.getEmail());

            if (existingPatient == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Patient not found");
            }

            if (!existingPatient.getPassword().equals(patient.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid password");
            }

            String token = tokenService.generateToken(existingPatient.getEmail());
            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed");
        }
    }
}
