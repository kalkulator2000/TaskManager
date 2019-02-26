//package com.example.TaskManager;
//
//import com.example.TaskManager.entity.Task;
//import com.example.TaskManager.entity.User;
//import com.example.TaskManager.service.TaskService;
//import com.example.TaskManager.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TaskManagerApplicationTests {
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private TaskService taskService;
//
//	@Before
//	public void initDb() {
//		{
//			User newUser = new User("user1@mail.com", "user1", "user1");
//			userService.createUser(newUser);
//		}
//		{
//			User newUser = new User("user2@mail.com", "user2", "user2");
//			userService.createUser(newUser);
//		}
//		{
//			User newUser = new User("admin@mail.com", "admin", "admin");
//			userService.createAdmin(newUser);
//		}
//
//		Task user1Task = new Task("Washing", LocalDate.of(2018,12,25), "You need to do some washing");
//		User user1 = userService.findOne("user1@mail.com");
//		taskService.addTask(user1Task, user1);
//
//		Task user2Task = new Task("Ironing", LocalDate.of(2018,12,25), "You need to do some ironing");
//		User user2 = userService.findOne("user2@mail.com");
//		taskService.addTask(user2Task, user2);
//	}
//
//	@Test
//	public void testUser() {
//		User user = userService.findOne("user1@mail.com");
//		assertNotNull(user);
//		User admin = userService.findOne("admin@mail.com");
//		assertEquals(admin.getEmail(),"admin@mail.com");
//	}
//
//	@Test
//	public void testTask() {
//		User user = userService.findOne("user1@mail.com");
//		List<Task> task = taskService.findByUser(user);
//		assertNotNull(task);
//
//	}
//
//}
