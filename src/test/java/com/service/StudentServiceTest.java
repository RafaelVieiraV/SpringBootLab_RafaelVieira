package com.service;

import com.domain.Student;
import com.dto.StudentCreateRequest;
import com.repository.StudentRepository;
import com.service.impl.StudentServiceImpl;
import com.web.advice.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy; // Importante para que funcione el test

@DataJpaTest
@Import({StudentServiceImpl.class})
public class StudentServiceTest {

    @Autowired
    private StudentServiceImpl studentService; // Asegúrate de usar la implementación

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldNotAllowDuplicatedEmail() {
        // 1. Guardamos un estudiante previo con el email que queremos duplicar
        Student existing = new Student();
        existing.setFullName("Existing User");
        existing.setEmail("duplicated@example.com");
        existing.setBirthDate(LocalDate.of(2001, 12, 1));
        existing.setActive(true);
        studentRepository.save(existing); // Usamos el nombre correcto del repositorio

        // 2. Preparamos la solicitud para crear uno nuevo con el mismo email
        StudentCreateRequest request = new StudentCreateRequest();
        request.setFullName("New User");
        request.setEmail("duplicated@example.com");
        request.setBirthDate(LocalDate.of(2001, 12, 1));

        // 3. Verificamos que al intentar guardarlo, lance la ConflictException
        assertThatThrownBy(() -> studentService.create(request))
                .isInstanceOf(ConflictException.class);
    }
}