package com.example.course2208.service;

import com.example.course2208.exception.NoGradesException;
import com.example.course2208.exception.StudentNotFoundException;
import com.example.course2208.model.Grade;
import com.example.course2208.model.Specialty;
import com.example.course2208.model.Student;
import com.example.course2208.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void addGrade(Grade grade, Integer studentId) throws StudentNotFoundException {
        Optional<Student> tmpOptionalStudent = studentRepository.findById(studentId);
        if (tmpOptionalStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            tmpOptionalStudent.get().getGrades().add(grade);
            studentRepository.save(tmpOptionalStudent.get());
        }
    }

    public List<Grade> getAllGradesByStudent(Integer studentId) throws StudentNotFoundException {
        Optional<Student> tmpOptionalStudent = studentRepository.findById(studentId);
        if (tmpOptionalStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            Student tmpStudent = tmpOptionalStudent.get();
            return tmpStudent.getGrades();
        }
    }

    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentRepository.findAll().stream().filter((Student student) -> {
            try {
                return student.getAnnualAverageGrade() >= 8;
            } catch (NoGradesException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Integer getAverageGradeBySpecialty(Integer specialtyId) throws StudentNotFoundException, NoGradesException {
        Optional<List<Student>> tmpOptionalListStudent =
                Optional.ofNullable(studentRepository.findAllStudentsBySpecialty(specialtyId));
        if (tmpOptionalListStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            List<Student> studentsFromSpecialty = tmpOptionalListStudent.get();
            Integer averageGrade = 0;
            for (Student student : studentsFromSpecialty) {
                averageGrade += student.getAnnualAverageGrade();
            }
             return averageGrade / studentsFromSpecialty.size();
        }
    }

    public Student getStudentWithHighestGrade() throws StudentNotFoundException, NoGradesException {
        Optional<List<Student>> tmpOptionalListStudent = Optional.of(studentRepository.findAll());
        if (tmpOptionalListStudent.isEmpty()) {
            throw new StudentNotFoundException();
        }
        else{
            List<Student> students= tmpOptionalListStudent.get();
            Integer highestGrade = 0;
            Student smartestStudent = null;
            for (Student student : students) {
                if (student.getAnnualAverageGrade() > highestGrade) {
                    highestGrade = student.getAnnualAverageGrade();
                    smartestStudent = student;
                }
            }
            return smartestStudent;
        }
    }
    public Student getStudentWithHighestGradeFromSpecialty(Integer specialtyId)
            throws StudentNotFoundException, NoGradesException {
        Optional<List<Student>> tmpOptionalListStudent =
                Optional.of(studentRepository.findAllStudentsBySpecialty(specialtyId));
        if (tmpOptionalListStudent.isEmpty()) {
            throw new StudentNotFoundException();
        }
        else{
            List<Student> students= tmpOptionalListStudent.get();
            Integer highestGrade = 0;
            Student smartestStudent = null;
            for (Student student : students) {
                if (student.getAnnualAverageGrade() > highestGrade) {
                    highestGrade = student.getAnnualAverageGrade();
                    smartestStudent = student;
                }
            }
            return smartestStudent;
        }
    }
}
