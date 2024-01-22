package org.example.demo2.repository;

import org.example.demo2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByNameContaining(String name, Pageable pageable);

    Page<Student> findAll(Pageable pageable);
}
