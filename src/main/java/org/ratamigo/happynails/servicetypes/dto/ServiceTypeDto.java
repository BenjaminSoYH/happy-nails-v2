package org.ratamigo.happynails.servicetypes.dto;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import org.ratamigo.happynails.nailtechs.model.NailTech;

@Data
public class ServiceTypeDto {
    private int id;
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
}
