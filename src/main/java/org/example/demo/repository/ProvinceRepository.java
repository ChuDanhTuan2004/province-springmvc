package org.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.demo.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Iterable<Province> findAllByNameContaining (String name);
}