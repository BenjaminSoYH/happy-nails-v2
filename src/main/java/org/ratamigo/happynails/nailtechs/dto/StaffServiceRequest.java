package org.ratamigo.happynails.nailtechs.dto;

import lombok.Data;

import java.util.List;

@Data
public class StaffServiceRequest {
    private List<Integer> serviceIds;
}
