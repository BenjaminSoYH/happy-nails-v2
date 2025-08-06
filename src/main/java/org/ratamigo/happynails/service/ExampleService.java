package org.ratamigo.happynails.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExampleService {
    private final Map<String, String> exampleStore = new HashMap<>();

    public String getExample(String key) {
        return exampleStore.getOrDefault(key, null);
    }

    public Map<String, String> getAllExamples() {
        return new HashMap<>(exampleStore);
    }

    public String addOrUpdateExample(String key, String value) {
        exampleStore.put(key, value);
        return "Example added or updated: " + key + " = " + value;
    }
}