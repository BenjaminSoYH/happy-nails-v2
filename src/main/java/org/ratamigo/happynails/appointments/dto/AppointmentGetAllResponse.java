package org.ratamigo.happynails.appointments.dto;

import java.util.List;

import lombok.Data;

@Data
public class AppointmentGetAllResponse {
    private List<AppointmentDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
