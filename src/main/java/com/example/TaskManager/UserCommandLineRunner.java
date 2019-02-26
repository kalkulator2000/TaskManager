//package com.example.TaskManager;
//
//import com.example.TaskManager.entity.Task;
//import com.example.TaskManager.entity.User;
//import com.example.TaskManager.service.TaskService;
//import com.example.TaskManager.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//public class UserCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    TaskService taskService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User admin = new User("admin@email.com","admin","admin");
//        userService.createAdmin(admin);
//
//        User user1 = new User("user1@email.com","user1","user1");
//        userService.createUser(user1);
//
//        User user2 = new User("user2@email.com","user2","user2");
//        userService.createUser(user2);
//
//        Task task1 = new Task("Washing", LocalDate.of(2019,01,12),"Wash all the dishes in the kitchen");
//        taskService.addTask(task1,user1);
//
//        Task task2 = new Task("Ironing", LocalDate.of(2019,01,23),"Do the ironing");
//        taskService.addTask(task2,user1);
//
//        Task task3 = new Task("Vacuuming", LocalDate.of(2019,02,01),"Vacuum second floor.");
//        taskService.addTask(task3,user2);
//    }
//}
