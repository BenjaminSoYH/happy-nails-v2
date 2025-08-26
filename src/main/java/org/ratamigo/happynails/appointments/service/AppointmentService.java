package org.ratamigo.happynails.appointments.service;

import org.ratamigo.happynails.appointments.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    // Get all
    List<AppointmentDTO> getAppointments();
    // Get detail
    AppointmentDTO getAppointmentById(int id);
    // Create
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    // Update
    AppointmentDTO updateAppointment(int id, AppointmentDTO appointmentDTO);
    // Delete by id
    void deleteAppointmentById(int id);

    // TODO: Implement delete appointments by customer name
    void deleteAppointmentsByCustomerName(String name);

    List<AppointmentDTO> getAppointmentsByCustomerId(int id);
}
