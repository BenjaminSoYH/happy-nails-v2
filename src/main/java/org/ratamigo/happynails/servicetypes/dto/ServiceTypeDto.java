package org.ratamigo.happynails.servicetypes.dto;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ServiceTypeDto {
    private int id;
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
}
