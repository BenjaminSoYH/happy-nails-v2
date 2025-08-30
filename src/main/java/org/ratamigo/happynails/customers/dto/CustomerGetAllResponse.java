package org.ratamigo.happynails.customers.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerGetAllResponse {
    private List<CustomerDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
