package com.example.course2208.repository;

import com.example.course2208.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {

}
