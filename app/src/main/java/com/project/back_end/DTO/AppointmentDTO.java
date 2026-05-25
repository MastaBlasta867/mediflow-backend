package com.project.back_end.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {

    private Long patientId;
    private Long doctorId;

    private String date;   // ISO format: "2024-05-10"
    private String time;   // ISO format: "14:30"

    private String notes;
}
