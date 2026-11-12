package com.example.school.management.program.service;

import com.example.school.management.program.model.ClassRoom;
import com.example.school.management.program.model.Role;
import com.example.school.management.program.model.Student;
import com.example.school.management.program.model.User;
import com.example.school.management.program.repository.ClassRoomRepository;
import com.example.school.management.program.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;
    private final ClassRoomRepository classRoomRepository;

    public StudentService(StudentRepository studentRepository, UserService userService, ClassRoomRepository classRoomRepository) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.classRoomRepository = classRoomRepository;
    }

    @Transactional
    public Student createStudent(String admissionNo, String rawPassword, String firstName,
                                 String surname, String secondName, LocalDate dob, Long classRoomId) {


        User user = userService.registerUser(admissionNo, rawPassword, Role.STUDENT);


        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new IllegalArgumentException("ClassRoom not found with ID: " + classRoomId));


        Student student = new Student();
        student.setUser(user);
        student.setFirstName(firstName);
        student.setSurname(surname);
        student.setSecondName(secondName);
        student.setDob(dob);
        student.setClassRoom(classRoom);

        return studentRepository.save(student);
    }
}