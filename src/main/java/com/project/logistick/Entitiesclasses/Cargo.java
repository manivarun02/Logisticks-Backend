package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ AUTO GENERATED
    private Integer id;

    @Size(min = 2, max = 15)
    @NotNull
    private String name;

    @Size(min = 5, max = 1000000)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private int weight;

    @Positive
    @NotNull
    @Min(1)
    @Max(100)
    private int count;

    // ================= CONSTRUCTORS =================
    public Cargo() {}

    public Cargo(String name, String description, int weight, int count) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.count = count;
    }

    // ================= GETTERS & SETTERS =================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
