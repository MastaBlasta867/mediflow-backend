package com.project.back_end.controllers;

import com.project.back_end.DTO.AppointmentDTO;
import com.project.back_end.models.Appointment;
import com.project.back_end.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // BOOK APPOINTMENT
    @PostMapping
    public Appointment bookAppointment(@RequestBody AppointmentDTO dto) {
        return appointmentService.bookAppointment(dto);
    }

    // GET APPOINTMENTS FOR PATIENT
    @GetMapping("/patient/{id}")
    public List<Appointment> getAppointmentsForPatient(@PathVariable Long id) {
        return appointmentService.getAppointmentsForPatient(id);
    }

    // GET APPOINTMENTS FOR DOCTOR
    @GetMapping("/doctor/{id}")
    public List<Appointment> getAppointmentsForDoctor(@PathVariable Long id) {
        return appointmentService.getAppointmentsForDoctor(id);
    }

    // CANCEL APPOINTMENT
    @DeleteMapping("/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "Appointment cancelled";
    }
}
