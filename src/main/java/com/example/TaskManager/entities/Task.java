package com.example.TaskManager.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String date;
    @NotEmpty
    private String name;
    @NotEmpty
    private String deadline;
    @NotEmpty
    @Column(length=1000)
    private String description;
    @ManyToOne
    @JoinColumn(name="USER_EMAIL")
    private User user;

    public Task() {
    }

    public Task(@NotEmpty String date, @NotEmpty String name, @NotEmpty String deadline, @NotEmpty String description, User user) {
        this.date = date;
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.user = user;
    }

    public Task(@NotEmpty String date, @NotEmpty String name, @NotEmpty String deadline, @NotEmpty String description) {
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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

    public String showTask() {
        return "Task " + id + ", " + date+ ", " + name + ", deadline: " + deadline + ", user: " + user.getName();
    }

    public String showDetailedTask() {
        String s = showTask();
        return s + "\n\tDescription: " + description;
    }
}
