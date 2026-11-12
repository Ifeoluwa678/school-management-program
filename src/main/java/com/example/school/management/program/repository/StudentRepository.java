package com.example.school.management.program.repository;

import com.example.school.management.program.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
