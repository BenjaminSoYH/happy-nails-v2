package org.ratamigo.happynails.availabilities.model;

    import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.ratamigo.happynails.shared.TimeSlot;

import com.fasterxml.jackson.annotation.JsonBackReference;

//availabilityID (PK)
// dateTime
// techId(FK) - (cascade delete null)


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private TimeSlot timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id")
    @JsonBackReference(value="tech-availabilities")
    private NailTech nailTech;
}

