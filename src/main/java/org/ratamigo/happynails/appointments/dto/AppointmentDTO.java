package org.ratamigo.happynails.appointments.dto;

import lombok.Data;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.model.TimeSlot;

@Data
public class AppointmentDTO {
    private int id;
    private TimeSlot timeSlot;

    // Customer information
    private int customer_id;
    private String customer_name;

    // Tech information
    private int tech_id;
    private String tech_name;

    // Service information
    private int service_id;
    private String service_name;

    private Appointment.ApptStatus apptStatus;
}
