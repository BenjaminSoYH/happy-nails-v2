package org.ratamigo.happynails.servicetypes.dto;
import lombok.Data;

@Data
public class ServiceTypeDto {
    private int id;
    private String name;
    private String description;
    private int duration;
}
