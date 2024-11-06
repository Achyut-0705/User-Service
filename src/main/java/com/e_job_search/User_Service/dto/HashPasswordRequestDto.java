package com.e_job_search.User_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashPasswordRequestDto {
    private String hashedPassword;
    private String rawPassword;
}
