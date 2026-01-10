package com.hospitalManagement.service;

import com.hospitalManagement.dto.PatientDTO;
import com.hospitalManagement.entity.Patient;
import com.hospitalManagement.repository.PatientRepository;
import com.hospitalManagement.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = mapperUtil.mapToPatientEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return mapperUtil.mapToPatientDTO(savedPatient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(mapperUtil::mapToPatientDTO)
                .collect(Collectors.toList());
    }

    public Optional<PatientDTO> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(mapperUtil::mapToPatientDTO);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDetails) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setContactNumber(patientDetails.getContactNumber());
        patient.setAddress(patientDetails.getAddress());

        Patient updatedPatient = patientRepository.save(patient);
        return mapperUtil.mapToPatientDTO(updatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
