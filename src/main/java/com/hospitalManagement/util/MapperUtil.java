package com.hospitalManagement.util;

import com.hospitalManagement.dto.AppointmentDTO;
import com.hospitalManagement.dto.DoctorDTO;
import com.hospitalManagement.dto.PatientDTO;
import com.hospitalManagement.entity.Appointment;
import com.hospitalManagement.entity.Doctor;
import com.hospitalManagement.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    // Patient Mapping
    public PatientDTO mapToPatientDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getContactNumber(),
                patient.getAddress());
    }

    public Patient mapToPatientEntity(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getAge(),
                patientDTO.getGender(),
                patientDTO.getContactNumber(),
                patientDTO.getAddress());
    }

    // Doctor Mapping
    public DoctorDTO mapToDoctorDTO(Doctor doctor) {
        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getContactNumber());
    }

    public Doctor mapToDoctorEntity(DoctorDTO doctorDTO) {
        return new Doctor(
                doctorDTO.getId(),
                doctorDTO.getName(),
                doctorDTO.getSpecialization(),
                doctorDTO.getContactNumber());
    }

    // Appointment Mapping
    public AppointmentDTO mapToAppointmentDTO(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getStatus());
    }

    public Appointment mapToAppointmentEntity(AppointmentDTO appointmentDTO) {
        return new Appointment(
                appointmentDTO.getId(),
                appointmentDTO.getPatientId(),
                appointmentDTO.getDoctorId(),
                appointmentDTO.getAppointmentDate(),
                appointmentDTO.getStatus());
    }
}
