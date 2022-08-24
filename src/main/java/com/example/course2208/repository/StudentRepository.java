package com.example.course2208.repository;

import com.example.course2208.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findAllStudentsBySpecialty(Integer specialtyId);
}
