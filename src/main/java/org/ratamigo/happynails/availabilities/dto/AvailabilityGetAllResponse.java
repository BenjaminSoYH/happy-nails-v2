package org.ratamigo.happynails.availabilities.dto;

import java.util.List;

import lombok.Data;

@Data
public class AvailabilityGetAllResponse {
    private List<AvailabilityDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
