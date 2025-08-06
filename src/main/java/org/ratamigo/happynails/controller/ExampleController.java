package org.ratamigo.happynails.controller;

import org.ratamigo.happynails.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getExample(@PathVariable String key) {
        String value = exampleService.getExample(key);
        return value != null ? ResponseEntity.ok(value) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getAllExamples() {
        return ResponseEntity.ok(exampleService.getAllExamples());
    }

    @PostMapping("/{key}")
    public ResponseEntity<String> updateExample(@PathVariable String key, @RequestParam String value) {
        String response = exampleService.addOrUpdateExample(key, value);
        return ResponseEntity.status(201).body(response);
    }
}