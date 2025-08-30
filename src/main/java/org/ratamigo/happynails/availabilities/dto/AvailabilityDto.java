package org.ratamigo.happynails.availabilities.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AvailabilityDto {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer nailTechId; 
}

