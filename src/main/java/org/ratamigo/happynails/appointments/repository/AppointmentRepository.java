package org.ratamigo.happynails.appointments.repository;

import org.ratamigo.happynails.appointments.dto.AppointmentDTO;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    void deleteByCustomerNameIgnoreCase(String customerName);
    List<Appointment> getAppointmentsByCustomerId(int id);
}
