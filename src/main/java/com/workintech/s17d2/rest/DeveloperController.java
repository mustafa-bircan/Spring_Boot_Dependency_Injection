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
        double juniorNetSalary = 5000 - (5000 * taxable.getSimpleTaxRate() / 100);
        double midNetSalary = 8000 - (8000 * taxable.getMiddleTaxRate() / 100);
        double seniorNetSalary = 12000 - (12000 * taxable.getUpperTaxRate() / 100);

        developers.put(1, new JuniorDeveloper(1, "Ali", juniorNetSalary));
        developers.put(2, new MidDeveloper(2, "Ayşe", midNetSalary));
        developers.put(3, new SeniorDeveloper(3, "Mehmet", seniorNetSalary));
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
        double netSalary = calculateNetSalary(developer);

        Developer newDev = switch (developer.getExperience()) {
            case JUNIOR -> new JuniorDeveloper(developer.getId(), developer.getName(), netSalary);
            case MID -> new MidDeveloper(developer.getId(), developer.getName(), netSalary);
            case SENIOR -> new SeniorDeveloper(developer.getId(), developer.getName(), netSalary);
        };

        developers.put(newDev.getId(), newDev);

        return "Developer added successfully!";
    }

    private double calculateNetSalary(Developer developer) {
        double taxRate = switch (developer.getExperience()) {
            case JUNIOR -> taxable.getSimpleTaxRate();
            case MID -> taxable.getMiddleTaxRate();
            case SENIOR -> taxable.getUpperTaxRate();
        };
        return developer.getSalary() - (developer.getSalary() * taxRate / 100);
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
