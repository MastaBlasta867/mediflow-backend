package com.project_back_end.controllers;

import com.project_back_end.models.Admin;
import com.project_back_end.services.ServiceClass;
import com.project_back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ServiceClass serviceClass;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            Admin savedAdmin = serviceClass.saveAdmin(admin);
            return ResponseEntity.ok(savedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering admin");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
        try {
            Admin existingAdmin = serviceClass.getAdminByEmail(admin.getEmail());

            if (existingAdmin == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Admin not found");
            }

            if (!existingAdmin.getPassword().equals(admin.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid password");
            }

            String token = tokenService.generateToken(existingAdmin.getEmail());
            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed");
        }
    }
}
