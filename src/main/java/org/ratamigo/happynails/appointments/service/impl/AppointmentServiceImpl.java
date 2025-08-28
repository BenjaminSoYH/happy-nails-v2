package org.ratamigo.happynails.appointments.service.impl;

import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.appointments.repository.AppointmentRepository;
import org.ratamigo.happynails.appointments.service.AppointmentService;
import org.ratamigo.happynails.repository.CustomerRepository;
import org.ratamigo.happynails.repository.NailTechRepository;
import org.ratamigo.happynails.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private CustomerRepository customerRepository;
    private NailTechRepository nailTechRepository;
    private ServiceTypeRepository serviceTypeRepository;

    // Implement mappers
    public AppointmentDTO mapToDto(Appointment appointment){
        // Create new DTO
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setTimeSlot(appointment.getTimeSlot());

        // Customer information
        dto.setCustomer_id(appointment.getCustomer().getId());
        dto.setCustomer_name(appointment.getCustomer().getName());

        // Nail tech information
        dto.setTech_id(appointment.getNailTech().getId());
        dto.setTech_name(appointment.getNailTech().getName());

        // Service information
        dto.setService_id(appointment.getService().getId());
        dto.setService_name(appointment.getService().getName());

        dto.setApptStatus(appointment.getStatus());
        return dto;
    }

    public Appointment mapToEntity(AppointmentDTO dto){
        // Create new DTO
        Appointment appointment = new Appointment();
        appointment.setTimeSlot(dto.getTimeSlot());
        appointment.setCustomer(customerRepository.findById(dto.getCustomer_id()).orElseThrow());
        appointment.setNailTech(nailTechRepository.findById(dto.getTech_id()).orElseThrow());
        appointment.setService(serviceTypeRepository.findById(dto.getService_id()).orElseThrow());
        appointment.setStatus(dto.getApptStatus());
        return appointment;
    }

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  CustomerRepository customerRepository,
                                  NailTechRepository nailTechRepository,
                                  ServiceTypeRepository serviceTypeRepository){
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.nailTechRepository = nailTechRepository;
        this.serviceTypeRepository = serviceTypeRepository;
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
        appointment.setCustomer(customerRepository.findById(dto.getCustomer_id()).orElseThrow());
        appointment.setNailTech(nailTechRepository.findById(dto.getTech_id()).orElseThrow());
        appointment.setService(serviceTypeRepository.findById(dto.getService_id()).orElseThrow());
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
