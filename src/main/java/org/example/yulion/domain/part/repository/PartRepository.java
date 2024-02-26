package org.example.yulion.domain.part.repository;

import org.example.yulion.domain.part.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>{
    Optional<Part> findByName(String name);
}
