package com.service;

import com.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponse create(StudentCreateRequest request);
    StudentResponse getById(Long id);

    // Cambiamos List por Page para paginación (Actividad 1)
    Page<StudentResponse> list(String name, Pageable pageable);

    // Usamos el DTO de actualización (Actividad 2)
    StudentResponse update(Long id, StudentUpdateDTO request);

    StudentResponse deactivate(Long id);
}