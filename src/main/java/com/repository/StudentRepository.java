package com.repository;

import com.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);

    // Actividad 1: Búsqueda por nombre (ignora mayúsculas/minúsculas) y paginación
    Page<Student> findByFullNameContainingIgnoreCase(String name, Pageable pageable);
}