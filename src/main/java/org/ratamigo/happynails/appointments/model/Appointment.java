package org.ratamigo.happynails.appointments.model;

//appointmentID (PK)
// userID (FK) - (cascade delete null)
// serviceID (FK)
// techId(FK)
// availabilityID (FK)
// status

//TODO: figure out what actually doesn't need a lazy fetch type. is there anything that should be 
// completely loaded immediately once one entity is accessed?
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.ratamigo.happynails.customers.model.Customer;
import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.ratamigo.happynails.servicetypes.model.ServiceType;
import org.ratamigo.happynails.shared.TimeSlot;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Appointment ID
    private int id;

    @Embedded
    private TimeSlot timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "customer-appointments")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nailTech_id")
    @JsonBackReference(value = "tech-appointments")
    private NailTech nailTech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    @JsonBackReference(value = "service-appointments")
    private ServiceType service;

    // Appointment status
    public enum ApptStatus {
        PENDING,        // Appointment requested but not yet confirmed
        CONFIRMED,      // Approved and scheduled
        CANCELLED,      // Cancelled by customer or salon
        COMPLETED,      // Service was provided
        NO_SHOW,        // Customer didn’t show up
        RESCHEDULED     // Appointment was moved to a new time
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ApptStatus", nullable = false)
    private ApptStatus status;
}


