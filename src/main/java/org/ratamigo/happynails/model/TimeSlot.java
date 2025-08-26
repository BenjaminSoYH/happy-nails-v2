package org.ratamigo.happynails.model;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    public boolean overlaps(TimeSlot other) {
        return !(endTime.isBefore(other.startTime) || startTime.isAfter(other.endTime));
    }
}

