package com.example.stumansys.serviceimpl;

import com.example.stumansys.repository.StudentRepository;
import com.example.stumansys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.stumansys.model.StudentModel;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentModel> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(StudentModel student) {
        studentRepository.save(student);
    }

    @Override
    public StudentModel getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
