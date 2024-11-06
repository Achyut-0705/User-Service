package com.e_job_search.User_Service.requests;

import com.e_job_search.User_Service.dto.HashPasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("auth-service")
public interface AuthServiceClient {
    @PostMapping("/auth/generate-hash-password")
    public ResponseEntity<String> generateHashPassword(@RequestBody String password);

    @PostMapping("/auth/compare-password")
    public ResponseEntity<Boolean> compareHashedPassword(@RequestBody HashPasswordRequestDto requestDto);
}
