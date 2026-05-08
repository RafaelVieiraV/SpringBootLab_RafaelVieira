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

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({StudentServiceImpl.class})
public class StudentServiceTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldNotAllowDuplicatedEmail() {
        // 1. Guardamos un estudiante previo
        Student existing = new Student();
        existing.setFullName("Existing User");
        existing.setEmail("duplicated@example.com");
        existing.setBirthDate(LocalDate.of(2001, 12, 1));
        existing.setActive(true);
        studentRepository.save(existing);

        // 2. CAMBIO PARA FALLAR: Usamos un email que NO es duplicado
        StudentCreateRequest request = new StudentCreateRequest();
        request.setFullName("New User");
        request.setEmail("este-email-no-es-duplicado@example.com"); // <--- Esto rompe el test
        request.setBirthDate(LocalDate.of(2001, 12, 1));

        // 3. El test fallará aquí porque espera una ConflictException que NO va a ocurrir
        assertThatThrownBy(() -> studentService.create(request))
                .isInstanceOf(ConflictException.class);
    }
}