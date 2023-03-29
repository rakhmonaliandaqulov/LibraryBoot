package com.example.service;

import org.example.dto.Student;
import org.example.enums.GeneralStatus;
import org.example.repository.StudentRepository;
import org.example.repository.StudentsBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentsBookRepository studentsBookRepository;
    public void addStudent(String name, String surname, String phone, String birthDate) {
        Student exists = studentRepository.getStudentByPhone(phone);
        if (exists != null) {
            System.out.println("\n Student phone exists.");
        }

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPhone(phone);
        student.setBirthDate(LocalDate.parse(birthDate));
        student.setStatus(GeneralStatus.STUDENT);

        int n = studentRepository.save(student);

        if (n != 0) {
            System.out.println("\n Student added successfully");
            return;
        } else {
            System.out.println("\n ERROR");
        }
    }

    public void studentList() {
        List<Student> studentList = studentRepository.studentList();
        if (studentList == null) {
            System.out.println("\n No student yet");
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void deleteStudent(Integer id) {
        Student exists = studentRepository.getStudentById(id);
        if (exists == null) {
            System.out.println("\n Not found student.");
        }
        int n = studentRepository.deleteStudent(id);
        if (n != 0) {
            System.out.println("\n Deleted student");
        }
    }
}
