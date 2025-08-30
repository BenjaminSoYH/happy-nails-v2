package org.ratamigo.happynails.shared;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    public boolean overlaps(TimeSlot other) {
        return !(endTime.isBefore(other.startTime) || startTime.isAfter(other.endTime));
    }
}

