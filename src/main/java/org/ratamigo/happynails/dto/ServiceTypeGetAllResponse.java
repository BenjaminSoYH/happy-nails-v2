package org.ratamigo.happynails.dto;

import java.util.List;

import lombok.Data;

@Data
public class ServiceTypeGetAllResponse {
    private List<ServiceTypeDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
