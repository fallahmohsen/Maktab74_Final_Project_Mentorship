package com.example.online_quiz2.controller;

import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.service.CourseService;
import com.example.online_quiz2.service.ManagerService;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    public static List<User> userList;
    private final ManagerService managerService;
    private final UserService userService;
    private final CourseService courseService;

    @GetMapping("/login")
    public String getLogin() {
        return "login_manager";
    }

    @GetMapping("/checkInfo")
    public String checkInfo(@RequestParam("username") String username, @RequestParam("password") String password) {
        managerService.loginManager(password, username);
        return "firstMenuManger";
    }

    @GetMapping("/showList")
    public String showListUser(Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("user", user);

        return "showAllUser";
    }

    @RequestMapping("/editInfo")
    public String editInfo(@RequestParam("button") String button) {
        //    User user = new User(username,password,name,null,"accepted",null);
        //System.out.println(name);
        Long id = Long.valueOf(button);
        Optional<User> user = userService.findCustom(id);
        userService.updateUser(user);
        return "redirect:/manager/showList";
    }

    @GetMapping("/load-search")
    public String loadSearch() {
        return "search";
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name, @RequestParam("gender") String gender, @RequestParam("role")
            String role, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("status") String status, Model model) {
        User user = new User(username, password, name, gender, status, role);
        userList = userService.AllByAdvanceSearch(user);
        return "redirect:/manager/table-search";
    }

    @GetMapping("/table-search")
    public String showTableSearch(Model model) {
        model.addAttribute("userList", userList);
        return "SearchTable";
    }

    @GetMapping("/show-course")
    public String showCourse(Model model) {
        List<Course> course = courseService.findAll();
        model.addAttribute("course", course);
        return "courseTable";
    }

    @GetMapping("/addCourse")
    public String addCourse(Model model) {
        return "addCourse";
    }

    @GetMapping("/save-course")
    public String saveCourse(@RequestParam("titleOfCourse") String titleOfCourse, @RequestParam("uniqueIdOfTheCourse") String uniqueIdOfTheCourse,
                             @RequestParam("courseStartDate") String courseStartDate, @RequestParam("courseEndDate") String courseEndDate) throws FileNotFoundException, ParseException {
        Course course = new Course(null, titleOfCourse, uniqueIdOfTheCourse, null, null, null);
        courseService.save(course, courseEndDate, courseStartDate);
        return "successCourse";
    }

    @GetMapping("/first-menu")
    public String firstMenu() {
        return "firstMenuManger";
    }

    @GetMapping("/addProfessor")
    public String addProfessor(Model model) {
        List<User> userList1 = userService.findAllProfessor();
        List<Course> courseList = courseService.findAll();
        model.addAttribute("course", courseList);
        model.addAttribute("user", userList1);
        return "addNewProfessor";
    }

    @GetMapping("/saveProfessor")
    public String saveProfessor(@RequestParam("button") String id) {
        Long userId = Long.valueOf(userService.splitId(id));
        Long courseId = Long.valueOf(courseService.spiltCourseId(id));
        Optional<User> user = userService.findById(userId);
        Optional<Course> course = courseService.findById(courseId);
        courseService.saveProfessor(user, course);
        return "redirect:/manager/addProfessor";
    }

    @GetMapping("/university-student")
    public String addNewStudent(Model model) {
        List<User> user = userService.findAllByStudent();
        List<Course> courseList = courseService.findAll();
        model.addAttribute("course", courseList);
        model.addAttribute("user", user);
        return "addNewUniversityStudent";
    }

    @PostMapping("/add-student-course")
    public String addStudentCourse(@RequestParam("student") String student, @RequestParam("courseId") String id) {
        Optional<Course> course = courseService.findById(Long.valueOf(id));
        courseService.saveStudent(course, student);
        return "redirect:/manager/university-student";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(Model model) {
        List<Course> courseList = courseService.findAll();
        model.addAttribute("course", courseList);
        return "deleteStudent";
    }

    @PostMapping("/remove-student")
    public String removeStudent(@RequestParam("courseId") String id, @RequestParam("student") String student) {
        Optional<Course> course = courseService.findById(Long.valueOf(id));
        courseService.deleteStudent(course, student);
        return "redirect:/manager/delete-student";
    }

}
