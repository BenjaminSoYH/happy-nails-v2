package org.ratamigo.happynails.model;

//appointmentID (PK)
// userID (FK) - (cascade delete null)
// serviceID (FK)
// techId(FK)
// availabilityID (FK)
// status

//TODO: figure out what actually doesn't need a lazy fetch type. is there anything that should be 
// completely loaded immediately once one entity is accessed?
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private TimeSlot timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id")
    private NailTech nailTech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceType service;

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


