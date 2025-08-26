package org.ratamigo.happynails.appointments.service.impl;

import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.appointments.repository.AppointmentRepository;
import org.ratamigo.happynails.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;


    // Implement mappers
    public AppointmentDTO mapToDto(Appointment appointment){
        // Create new DTO
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setTimeSlot(appointment.getTimeSlot());
        dto.setCustomer(appointment.getCustomer());
        dto.setTech(appointment.getNailTech());
        dto.setService(appointment.getService());
        dto.setApptStatus(appointment.getStatus());
        return dto;
    }

    public Appointment mapToEntity(AppointmentDTO dto){
        // Create new DTO
        Appointment appointment = new Appointment();
        appointment.setTimeSlot(dto.getTimeSlot());
        appointment.setCustomer(dto.getCustomer());
        appointment.setNailTech(dto.getTech());
        appointment.setService(dto.getService());
        appointment.setStatus(dto.getApptStatus());
        return appointment;
    }

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        return mapToDto(appointment);
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment appointment = mapToEntity(dto);
        Appointment saved = appointmentRepository.save(appointment);
        return mapToDto(saved);
    }

    @Override
    public AppointmentDTO updateAppointment(int id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setTimeSlot(dto.getTimeSlot());
        appointment.setCustomer(dto.getCustomer());
        appointment.setNailTech(dto.getTech());
        appointment.setService(dto.getService());
        appointment.setStatus(dto.getApptStatus());
        Appointment saved = appointmentRepository.save(appointment);
        return mapToDto(saved);
    }

    @Override
    public void deleteAppointmentById(int id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void deleteAppointmentsByCustomerName(String name) {
        appointmentRepository.deleteByCustomerNameIgnoreCase(name);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByCustomerId(int id) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByCustomerId(id);
        return appointments.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
    }


}
