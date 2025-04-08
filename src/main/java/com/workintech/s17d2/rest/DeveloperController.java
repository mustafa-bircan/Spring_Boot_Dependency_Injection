package com.workintech.s17d2.rest;

import com.workintech.exception.DeveloperNotFoundException;
import com.workintech.s17d2.tax.Taxable;
import com.workintech.s17d2.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final Taxable taxable;
    public Map<Integer, Developer> developers = new HashMap<>();

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        developers.put(1, new Developer(1, "Ali", 5000, Experience.JUNIOR));
        developers.put(2, new Developer(2, "Ayşe", 8000, Experience.MID));
        developers.put(3, new Developer(3, "Mehmet", 12000, Experience.SENIOR));
    }

    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id) {
        Developer developer = developers.get(id);
        if (developer == null) {
            throw new DeveloperNotFoundException("Developer with ID " + id + " not found.");
        }
        return developer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addDeveloper(@RequestBody Developer developer) {
        if (developer.getExperience() == Experience.JUNIOR) {
            developers.put(developer.getId(), new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()));
        } else if (developer.getExperience() == Experience.MID) {
            developers.put(developer.getId(), new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()));
        } else if (developer.getExperience() == Experience.SENIOR) {
            developers.put(developer.getId(), new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()));
        }
        return "Developer added successfully!";
    }

    @PutMapping("/{id}")
    public String updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        Developer existingDeveloper = developers.get(id);
        if (existingDeveloper == null) {
            throw new DeveloperNotFoundException("Developer with ID " + id + " not found.");
        }

        if (developer.getId() != id) {
            throw new RuntimeException("ID in path and body must match.");
        }

        developers.put(id, developer);
        return "Developer updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteDeveloper(@PathVariable int id) {
        Developer developer = developers.remove(id);
        if (developer == null) {
            throw new DeveloperNotFoundException("Developer with ID " + id + " not found.");
        }
        return "Developer deleted successfully!";
    }
}
