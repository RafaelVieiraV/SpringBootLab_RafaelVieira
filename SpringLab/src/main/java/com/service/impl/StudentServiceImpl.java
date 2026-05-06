package com.service.impl;

import com.domain.Student;
import com.dto.*;
import com.repository.StudentRepository;
import com.service.StudentService;
import com.web.advice.ConflictException;
import com.web.advice.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentResponse create(StudentCreateRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email ya está registrado");
        }
        Student s = new Student();
        s.setFullName(request.getFullName());
        s.setEmail(request.getEmail());
        s.setBirthDate(request.getBirthDate());
        s.setActive(true);
        return toResponse(repository.save(s));
    }

    @Override
    public StudentResponse getById(Long id) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return toResponse(s);
    }

    @Override
    public Page<StudentResponse> list(String name, Pageable pageable) {
        Page<Student> students;
        if (name != null && !name.isEmpty()) {
            students = repository.findByFullNameContainingIgnoreCase(name, pageable);
        } else {
            students = repository.findAll(pageable);
        }
        return students.map(this::toResponse);
    }

    @Override
    public StudentResponse update(Long id, StudentUpdateDTO request) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        s.setFullName(request.getFullName());
        return toResponse(repository.save(s));
    }

    @Override
    public StudentResponse deactivate(Long id) {
        Student s = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        s.setActive(false);
        return toResponse(repository.save(s));
    }

    private StudentResponse toResponse(Student s) {
        StudentResponse r = new StudentResponse();
        r.setId(s.getId());
        r.setFullName(s.getFullName());
        r.setEmail(s.getEmail());
        return r;
    }
}