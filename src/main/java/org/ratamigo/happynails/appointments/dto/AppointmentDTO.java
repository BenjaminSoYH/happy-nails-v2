package org.ratamigo.happynails.appointments.dto;

import lombok.Data;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.model.Customer;
import org.ratamigo.happynails.model.NailTech;
import org.ratamigo.happynails.model.ServiceType;
import org.ratamigo.happynails.model.TimeSlot;

@Data
public class AppointmentDTO {
    private int id;
    private TimeSlot timeSlot;
    private Customer customer;
    private NailTech tech;
    private ServiceType service;
    private Appointment.ApptStatus ApptStatus;
}
