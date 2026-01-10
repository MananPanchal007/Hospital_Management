package com.hospitalManagement.service;

import com.hospitalManagement.dto.DoctorDTO;
import com.hospitalManagement.entity.Doctor;
import com.hospitalManagement.repository.DoctorRepository;
import com.hospitalManagement.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapperUtil.mapToDoctorEntity(doctorDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return mapperUtil.mapToDoctorDTO(savedDoctor);
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(mapperUtil::mapToDoctorDTO)
                .collect(Collectors.toList());
    }

    public Optional<DoctorDTO> getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(mapperUtil::mapToDoctorDTO);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDetails) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setContactNumber(doctorDetails.getContactNumber());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return mapperUtil.mapToDoctorDTO(updatedDoctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
