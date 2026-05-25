package com.project_back_end.controllers;

import com.project_back_end.models.Prescription;
import com.project_back_end.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<?> createPrescription(@RequestBody Prescription prescription) {
        try {
            Prescription saved = prescriptionService.savePrescription(prescription);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving prescription");
        }
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getPrescription(@PathVariable Long appointmentId) {
        try {
            Prescription prescription = prescriptionService.getPrescriptionByAppointmentId(appointmentId);

            if (prescription == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No prescription found for this appointment");
            }

            return ResponseEntity.ok(prescription);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving prescription");
        }
    }
}
