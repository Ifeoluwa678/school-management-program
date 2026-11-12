package com.example.school.management.program.service;

import com.example.school.management.program.model.Role;
import com.example.school.management.program.model.Teacher;
import com.example.school.management.program.model.User;
import com.example.school.management.program.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public TeacherService(TeacherRepository teacherRepository, UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
    }

    @Transactional
    public Teacher createTeacher(String email, String rawPassword, String firstName,
                                 String surname, String qualifications, String phone) {

        User user = userService.registerUser(email, rawPassword, Role.TEACHER);

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setFirstName(firstName);
        teacher.setSurname(surname);
        teacher.setQualifications(qualifications);
        teacher.setPhone(phone);

        return teacherRepository.save(teacher);
    }
}