package com.example.db;

import org.example.dto.Student;
import org.example.enums.GeneralStatus;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class InitDatabase {
    @Autowired
    StudentRepository studentRepository;
    public  void adminInit() {

        Student profile = new Student();
        profile.setName("Admin");
        profile.setSurname("Adminjon");
        profile.setPhone("123");
        profile.setBirthDate(LocalDate.parse("2000-12-12"));
        profile.setStatus(GeneralStatus.ADMIN);


        Student profile1 = studentRepository.getStudentByPhone(profile.getPhone());
        if (profile1 != null) {
            return;
        }
        studentRepository.save(profile);
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
