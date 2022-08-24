package com.example.course2208.controller;

import com.example.course2208.exception.NoGradesException;
import com.example.course2208.exception.StudentNotFoundException;
import com.example.course2208.model.Grade;
import com.example.course2208.model.Student;
import com.example.course2208.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("grades/{studentId}")
    public List<Grade> getAllGradesByStudent(@PathVariable Integer studentId)
            throws StudentNotFoundException {
        return studentService.getAllGradesByStudent(studentId);
    }

    @PutMapping("grades/add/{studentId}/{grade}")
    public void addGrade(@PathVariable Grade grade, @PathVariable Integer studentId)
            throws StudentNotFoundException {
        studentService.addGrade(grade, studentId);
    }

    @GetMapping("")
    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentService.getAllStudentsGradeGreaterThanEight();
    }

    @GetMapping("{specialtyId}/annualaveragegrade")
    public Integer getAnnualAverageGrade(@PathVariable Integer specialtyId)
            throws NoGradesException, StudentNotFoundException {
        return studentService.getAverageGradeBySpecialty(specialtyId);
    }

    @GetMapping("smartest/all")
    public Student getStudentWithHighestGrade() throws NoGradesException, StudentNotFoundException {
        return studentService.getStudentWithHighestGrade();
    }

    @GetMapping("smartest/specialty")
    public Student getStudentWithHighestGradeFromSpecialty(Integer specialtyId)
            throws NoGradesException, StudentNotFoundException {
        return studentService.getStudentWithHighestGradeFromSpecialty(specialtyId);
    }
}
