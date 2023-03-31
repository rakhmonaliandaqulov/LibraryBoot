package com.example.controller;

import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestExcception;
import com.example.service.CommandLineRunnerImpl;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private List<StudentEntity> studentList = new LinkedList<>();

    public StudentController() {
        StudentEntity s1 = new StudentEntity();
        s1.setId(1);
        s1.setName("Alish");
        s1.setSurname("Aliyev");

        studentList.add(s1);
    }

    @GetMapping("/list")
    public List<StudentEntity> getAll() {
        return studentService.studentList();
    }

    @GetMapping(value = "/get/{id}")
    public StudentEntity getById(@PathVariable("id") String id) {
        Optional<StudentEntity> optional = studentService.studentList().stream().filter(studentDTO -> studentDTO.getId().equals(id)).findAny();
        return optional.orElse(null);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentEntity studentEntity) {
        //return studentService.addStudent(studentEntity);
       // return new ResponseEntity<>("test message", HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

//        StudentEntity respnse = studentService.addStudent(studentEntity);
//        return new ResponseEntity<>(respnse, HttpStatus.OK);

//        StudentEntity respnse = studentService.addStudent(studentEntity);
//        return ResponseEntity.ok(respnse);

//        StudentEntity respnse = studentService.addStudent(studentEntity);
//        return ResponseEntity.ok().body(respnse);

        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //return ResponseEntity.badRequest().build();
        try {
            StudentEntity response = studentService.addStudent(studentEntity);
            return ResponseEntity.ok(response);
        }catch (AppBadRequestExcception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping(value = "/create/all")
    public Boolean createAll(@RequestBody List<StudentEntity> list) {
        for (StudentEntity dto : list) {
            dto.setId(1);
            studentList.add(dto);
        }
        return true;
    }

    @PutMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") String id, @RequestBody StudentEntity studentDTO) {
        for (StudentEntity dto : studentList) {
            if (dto.getId().equals(id)) {
                dto.setName(studentDTO.getName());
                dto.setSurname(studentDTO.getSurname());
                return true;
            }
        }
        return false;
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean delete(@PathVariable("id") String id) {
        return studentService.studentList().removeIf(studentDTO -> studentDTO.getId().equals(id));
    }
}
