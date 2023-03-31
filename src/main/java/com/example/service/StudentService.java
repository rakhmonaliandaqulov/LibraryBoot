package com.example.service;

import com.example.entity.StudentEntity;
import com.example.entity.StudentEntity1;
import com.example.exp.AppBadRequestExcception;
import com.example.repository.StudentBookRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentBookRepository studentBookRepository;
    public StudentEntity addStudent(StudentEntity student) {
        /*StudentEntity exists = studentRepository.getStudentByPhone(phone);
        if (exists != null) {
            System.out.println("\n Student phone exists.");
        }*/
        student.setName(student.getName());
        student.setSurname(student.getSurname());
        student.setPhone(student.getPhone());
        student.setBirthDate(student.getBirthDate());
        student.setStatus(student.getStatus());

        int n = studentRepository.save(student);

        if (n != 0) {
            System.out.println("\n Student added successfully");
            return student;
        } else {
            System.out.println("\n ERROR");
        }
        return student;
    }
    /*public StudentEntity1 create(StudentEntity1 entity) {
        StudentEntity1 student = new StudentEntity1();
        entity.setName(entity.getName());
        entity.setSurname(entity.getSurname());
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new AppBadRequestExcception("Where is name?");
        }
        if (entity.getSurname() == null || entity.getSurname().isBlank()) {
            throw new AppBadRequestExcception("Where is surname?");
        }
        studentRepository.save(student);

    }*/
    public List<StudentEntity> studentList() {
        List<StudentEntity> studentList = studentRepository.studentList();
        if (studentList == null) {
            System.out.println("\n No student yet");
        }
        for (StudentEntity student : studentList) {
            System.out.println(student);
        }
        return studentList;
    }

    public void deleteStudent(Integer id) {
        StudentEntity exists = studentRepository.getStudentById(id);
        if (exists == null) {
            System.out.println("\n Not found student.");
        }
        int n = studentRepository.deleteStudent(id);
        if (n != 0) {
            System.out.println("\n Deleted student");
        }
    }


}
