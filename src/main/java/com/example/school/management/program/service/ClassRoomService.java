package com.example.school.management.program.service;

import com.example.school.management.program.model.ClassRoom;
import com.example.school.management.program.model.Teacher;
import com.example.school.management.program.repository.ClassRoomRepository;
import com.example.school.management.program.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final TeacherRepository teacherRepository;

    public ClassRoomService(ClassRoomRepository classRoomRepository, TeacherRepository teacherRepository) {
        this.classRoomRepository = classRoomRepository;
        this.teacherRepository = teacherRepository;
    }

    // Create a new ClassRoom (e.g., Name: "JSS 1", Arm: "A")
    public ClassRoom createClassRoom(String name, String arm, Long formTeacherId) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName(name);
        classRoom.setArm(arm);

        if (formTeacherId != null) {
            Teacher formTeacher = teacherRepository.findById(formTeacherId)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + formTeacherId));
            classRoom.setFormTeacher(formTeacher);
        }

        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }

    public Optional<ClassRoom> getClassRoomById(Long id) {
        return classRoomRepository.findById(id);
    }
}