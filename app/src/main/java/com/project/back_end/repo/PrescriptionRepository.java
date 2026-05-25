package com.project_back_end.repositories;

import com.project_back_end.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findByAppointmentId(Long appointmentId);
}
