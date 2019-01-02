package com.example.TaskManager.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startingDate;
    @NotEmpty
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @Column(length=1000)
    private String description;

    @ManyToOne
    @JoinColumn(name="USER_EMAIL")
    @JsonBackReference
    private User user;


    public Task() {
        this.startingDate = LocalDate.now();
    }

    public Task(@NotEmpty String name, LocalDate deadline, String description, User user) {
        this.startingDate = LocalDate.now();
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.user = user;
    }

    public Task(@NotEmpty String name, LocalDate deadline, String description) {
        this.startingDate = LocalDate.now();
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate date) {
        this.startingDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
