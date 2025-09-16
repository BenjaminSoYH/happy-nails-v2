package org.ratamigo.happynails.nailtechs.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ratamigo.happynails.appointments.model.Appointment;
import org.ratamigo.happynails.availabilities.model.Availability;
import org.ratamigo.happynails.servicetypes.model.ServiceType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NailTech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    private String email;
    private String phone;

    private String path; // image path (file system or cloud url)

    // Map to the nailTech field in appointment
    @OneToMany(mappedBy="nailTech", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value="tech-appointments")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "nailTech", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="tech-availabilities")
    private List<Availability> availabilities; //TODO: Add all of this stuff everywhere

    // Many to many relationship
    @ManyToMany
    @JoinTable(
        name = "nailtech_service",
        joinColumns = @JoinColumn(name = "nailtech_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceType> services = new ArrayList<>();

}
