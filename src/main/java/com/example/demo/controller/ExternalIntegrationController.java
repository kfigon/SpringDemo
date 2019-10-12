package com.example.demo.controller;

import com.example.demo.entity.ExternalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/external")
@Slf4j
public class ExternalIntegrationController {

    @Value("${external.url}")
    private String URL;

    @GetMapping("/{id}")
    public ResponseEntity<ExternalData> getData(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(URL, ExternalData.class);
    }
}
