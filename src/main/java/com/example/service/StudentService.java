package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.GeneralStatus;
import com.example.repository.StudentBookRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentBookRepository studentBookRepository;
    public void addStudent(String name, String surname, String phone, String birthDate) {
        StudentEntity exists = studentRepository.getStudentByPhone(phone);
        if (exists != null) {
            System.out.println("\n Student phone exists.");
        }

        StudentEntity student = new StudentEntity();
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
        List<StudentEntity> studentList = studentRepository.studentList();
        if (studentList == null) {
            System.out.println("\n No student yet");
        }
        for (StudentEntity student : studentList) {
            System.out.println(student);
        }
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
