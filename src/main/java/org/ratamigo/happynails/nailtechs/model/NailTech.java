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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NailTech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String phone;

    // Map to the nailTech field in appointment
    @OneToMany(mappedBy="nailTech", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value="tech-appointments")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "nailTech", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Availability> availabilities; //TODO: Add all of this stuff everywhere

}
