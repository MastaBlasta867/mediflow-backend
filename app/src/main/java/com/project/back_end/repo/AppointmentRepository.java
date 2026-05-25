package com.project.back_end.repo;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project_back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date);
}
