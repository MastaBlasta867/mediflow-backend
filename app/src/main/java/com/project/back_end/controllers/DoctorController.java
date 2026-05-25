package com.project_back_end.controllers;

import com.project_back_end.models.Doctor;
import com.project_back_end.services.ServiceClass;
import com.project_back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = serviceClass.saveDoctor(doctor);
            return ResponseEntity.ok(savedDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering doctor");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor existingDoctor = serviceClass.getDoctorByEmail(doctor.getEmail());

            if (existingDoctor == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Doctor not found");
            }

            if (!existingDoctor.getPassword().equals(doctor.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid password");
            }

            String token = tokenService.generateToken(existingDoctor.getEmail());
            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed");
        }
    }
}
