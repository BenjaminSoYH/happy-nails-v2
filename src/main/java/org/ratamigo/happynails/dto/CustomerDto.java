package org.ratamigo.happynails.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int previousServiceId;
}
