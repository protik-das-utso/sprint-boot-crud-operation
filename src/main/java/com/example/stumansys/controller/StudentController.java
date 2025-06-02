package com.example.stumansys.controller;

import com.example.stumansys.model.StudentModel;
import com.example.stumansys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<StudentModel> StudentList = studentService.getStudents();
        model.addAttribute("StudentList", StudentList);

        return "index";
    }

    @ResponseBody
    @GetMapping("/api")
    public List<StudentModel> API() {
        return studentService.getStudents();
    }

    @GetMapping("/student/add")
    public String showAddStudentForm(Model model) {
        // Logic to add student
        model.addAttribute("student", new StudentModel());
        return "add-student";
    }
    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute("student") StudentModel student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/student/update/{id}")
    public String showStudentEditForm(@PathVariable Integer id, Model model){
        StudentModel student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update-student";
    }


    @PostMapping("/student/update/{id}")
    public String updateStudent(@PathVariable("id") Integer id, @ModelAttribute("student") StudentModel updatedStudent){
        StudentModel existingStudent = studentService.getStudentById(id);
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setAddress(updatedStudent.getAddress());
        studentService.saveStudent(existingStudent);

        System.out.println("Updating student: " + existingStudent.getId());
        System.out.println("Old name: " + existingStudent.getAddress());
        System.out.println("Updated name: " + updatedStudent.getAddress());

        return "redirect:/";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, Model model) {
        StudentModel student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "delete-student";
    }
    @PostMapping("/student/delete/{id}")
    public String confirmDeleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

}
