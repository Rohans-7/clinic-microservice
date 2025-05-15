package com.cs.doctorservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String name;
    private String specialty;
    private String email;
}

