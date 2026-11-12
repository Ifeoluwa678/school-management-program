package com.example.school.management.program.controller;

import com.example.school.management.program.service.ClassRoomService;
import com.example.school.management.program.service.StudentService;
import com.example.school.management.program.service.SubjectService;
import com.example.school.management.program.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClassRoomService classRoomService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public AdminController(ClassRoomService classRoomService,
                           SubjectService subjectService,
                           TeacherService teacherService,
                           StudentService studentService) {
        this.classRoomService = classRoomService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("classRooms", classRoomService.getAllClassRooms());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "admin/dashboard";
    }


    @PostMapping("/classrooms/create")
    public String createClassRoom(@RequestParam String name,
                                  @RequestParam String arm,
                                  @RequestParam(required = false) Long formTeacherId) {
        classRoomService.createClassRoom(name, arm, formTeacherId);
        return "redirect:/admin/dashboard?success=classroom";
    }


    @PostMapping("/subjects/create")
    public String createSubject(@RequestParam String name,
                                @RequestParam(required = false) Long teacherId) {
        subjectService.createSubject(name, teacherId);
        return "redirect:/admin/dashboard?success=subject";
    }

    @PostMapping("/teachers/register")
    public String registerTeacher(@RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String firstName,
                                  @RequestParam String surname,
                                  @RequestParam String qualifications,
                                  @RequestParam String phone) {
        teacherService.createTeacher(email, password, firstName, surname, qualifications, phone);
        return "redirect:/admin/dashboard?success=teacher";
    }


    @PostMapping("/students/register")
    public String registerStudent(@RequestParam String admissionNo,
                                  @RequestParam String password,
                                  @RequestParam String firstName,
                                  @RequestParam String surname,
                                  @RequestParam String secondName,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                  @RequestParam Long classRoomId) {
        studentService.createStudent(admissionNo, password, firstName, surname, secondName, dob, classRoomId);
        return "redirect:/admin/dashboard?success=student";
    }
}