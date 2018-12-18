package com.example.TaskManager.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String name;
    @Size(min = 4)
    private String password;

    // Brakowało CascadeType.MERGE - przez to nie można było uruchomić testów, które zasilały bazę danymi testowymi
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLES",
            joinColumns = {@JoinColumn(name = "USER_EMAIL", referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_NAME", referencedColumnName = "name")
    })
    private List<Role> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public User() {
    }

    public User(@Email @NotEmpty String email, @NotEmpty String name, @Size(min = 4) String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", email: " + email;
    }
}
