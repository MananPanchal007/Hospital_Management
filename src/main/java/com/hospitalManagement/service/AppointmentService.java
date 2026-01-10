package com.hospitalManagement.service;

import com.hospitalManagement.dto.AppointmentDTO;
import com.hospitalManagement.entity.Appointment;
import com.hospitalManagement.repository.AppointmentRepository;
import com.hospitalManagement.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = mapperUtil.mapToAppointmentEntity(appointmentDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapperUtil.mapToAppointmentDTO(savedAppointment);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(mapperUtil::mapToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public Optional<AppointmentDTO> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(mapperUtil::mapToAppointmentDTO);
    }

    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setPatientId(appointmentDetails.getPatientId());
        appointment.setDoctorId(appointmentDetails.getDoctorId());
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setStatus(appointmentDetails.getStatus());

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return mapperUtil.mapToAppointmentDTO(updatedAppointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
