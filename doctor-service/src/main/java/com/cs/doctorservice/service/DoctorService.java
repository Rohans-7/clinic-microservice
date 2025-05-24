package com.cs.doctorservice.service;

import com.cs.doctorservice.dto.DoctorDTO;
import com.cs.doctorservice.entity.Doctor;
import com.cs.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public List<DoctorDTO> getAllDoctors() {
        return repository.findAll().stream()
                .map(doc -> DoctorDTO.builder()
                        .id(doc.getId())
                        .name(doc.getName())
                        .specialty(doc.getSpecialty())
                        .email(doc.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        return repository.findById(id)
                .map(doc -> DoctorDTO.builder()
                        .id(doc.getId())
                        .name(doc.getName())
                        .specialty(doc.getSpecialty())
                        .email(doc.getEmail())
                        .build())
                .orElse(null);
    }

    public DoctorDTO saveDoctor(DoctorDTO dto) {
        Doctor saved = repository.save(Doctor.builder()
                .name(dto.getName())
                .specialty(dto.getSpecialty())
                        .email(dto.getEmail())
                .build());

        dto.setId(saved.getId());
        return dto;
    }

    public void deleteDoctor(Long id) {
        repository.deleteById(id);
    }
}

