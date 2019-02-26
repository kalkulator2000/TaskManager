package com.example.TaskManager.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getStartingDate(), task.getStartingDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStartingDate());
    }
}
