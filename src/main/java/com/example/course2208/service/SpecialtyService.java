package com.example.course2208.service;

import com.example.course2208.exception.SpecialtyNotFoundException;
import com.example.course2208.model.Specialty;
import com.example.course2208.model.Student;
import com.example.course2208.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public List<Student> getAllStudentBySpecialty(Integer specialtyId) throws SpecialtyNotFoundException {
        Optional<Specialty> tmpOptionalSpecialty = specialtyRepository.findById(specialtyId);
        if (tmpOptionalSpecialty.isEmpty()) {
            throw new SpecialtyNotFoundException();
        } else {
            return tmpOptionalSpecialty.get().getStudents();
        }
    }

    public Specialty getSpecialtyWithTheMostStudents() throws SpecialtyNotFoundException {
        Optional<List<Specialty>> tmpOptionalSpecialtyList= Optional.of(specialtyRepository.findAll());
        if(tmpOptionalSpecialtyList.isEmpty()){
            throw new SpecialtyNotFoundException();
        }
        else{
            List<Specialty> specialtyList = tmpOptionalSpecialtyList.get();
            Integer maxStudents = 0;
            Specialty specialtyWithMostStudents = null;
            for(Specialty specialty :specialtyList){
                if(specialty.getStudents().size() > maxStudents){
                    maxStudents = specialty.getStudents().size();
                    specialtyWithMostStudents = specialty;
                }
            }
            return specialtyWithMostStudents;
        }
    }
}
