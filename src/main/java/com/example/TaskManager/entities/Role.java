package com.example.TaskManager.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @Column(unique = true)
    private String name;

    // Nie jest to jakiś błąd, ale dwustronne mapowanie nie jest potrzebne przy naszych założeniach (nie będziemy nigdy pobierać uzytkowników dla podanej roli)
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
