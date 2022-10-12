package com.socle.springboot.testUnitaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestJunitController {

    @Autowired
    private TestJunitService testJunitService;

    @GetMapping("/getMessage")
    public String getMessage(@RequestParam String name) {
        return testJunitService.getMessage(name);
    }

    @GetMapping("/getMap")
    public Map<String, Integer[]> getMap() {
        return testJunitService.testArray(1, 2, 3);
    }

}
