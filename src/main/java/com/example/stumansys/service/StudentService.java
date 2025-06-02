package com.example.stumansys.service;
import java.util.List;
import com.example.stumansys.model.StudentModel;


public interface StudentService {
    List<StudentModel> getStudents();

    StudentModel getStudentById(Integer id);
    void saveStudent(StudentModel student);
    void deleteStudent(Integer id);
}