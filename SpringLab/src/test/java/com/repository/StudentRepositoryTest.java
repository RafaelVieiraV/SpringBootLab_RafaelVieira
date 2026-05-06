package com.repository;

import com.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat; // Falta este import estático para el assertThat

@DataJpaTest // Configura la base de datos H2 para la prueba
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldSaveAndFindStudentByEmail() {
        // Preparar datos
        Student student = new Student();
        student.setFullName("Test User");
        student.setEmail("test@example.com");
        student.setBirthDate(LocalDate.of(2001, 12, 1));
        student.setActive(true);

        studentRepository.save(student);

        // Verificación
        var result = studentRepository.findByEmail("test@example.com"); // Faltaba el ;
        assertThat(result).isPresent();
        assertThat(result.get().getFullName()).isEqualTo("Test User"); // Faltaba el ;

    }
}