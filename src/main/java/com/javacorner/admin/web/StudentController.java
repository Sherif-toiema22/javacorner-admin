package com.javacorner.admin.web;

import com.javacorner.admin.entity.Student;
import com.javacorner.admin.entity.User;
import com.javacorner.admin.service.StudentService;
import com.javacorner.admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.javacorner.admin.constants.JavaCornerConstants.KEYWORD;
import static com.javacorner.admin.constants.JavaCornerConstants.LIST_STUDENTS;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    private UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping(value = "/index")
    public String students(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Student> students = studentService.findStudentsByName(keyword);
        model.addAttribute(LIST_STUDENTS, students);
        model.addAttribute(KEYWORD, keyword);
        return "student-views/students";
    }

    @GetMapping(value = "/delete")
    public String deleteStudent(Long studentId, String keyword) {
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword=" + keyword;
    }

    @GetMapping(value = "/formUpdate")
    public String updateStudent(Model model, Long studentId) {
        //current Student
        Student student = studentService.loadStudentById(studentId);
        model.addAttribute("student", student);
        return "student-views/formUpdate";
    }

    @PostMapping(value = "/update")
    public String update(Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/index";
    }

    @GetMapping(value = "/formCreate")
    public String formStudents(Model model) {
        model.addAttribute("student", new Student());
        return "student-views/formCreate";
    }

    @PostMapping(value = "/save")
    public String save(Student student, BindingResult bindingResult) {
        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "There is already an account registered with this email");
        if (bindingResult.hasErrors()) return "student-views/formCreate";
        studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getEmail(), student.getUser().getPassword());
        return "redirect:/students/index";
    }
}
