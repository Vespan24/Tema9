package com.example.course2208.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany
    @NotNull
    List<Student> students = new ArrayList<Student>();

    @OneToMany
    @NotNull
    List<Course> courses = new ArrayList<Course>();
}
