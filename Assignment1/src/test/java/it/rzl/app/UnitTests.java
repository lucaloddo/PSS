package it.rzl.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTests {
    @InjectMocks
    StudentController studentController;
    @Mock
    StudentRepository studentRepository;

    @Test
    public void unitTest() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(123456, "Mario", "Rossi", "Biologia");
        Student student2 = new Student(123457, "Davide", "Bianchi", "Data Science");

        list.add(student1);
        list.add(student2);

        when(studentRepository.findAll()).thenReturn(list);

        Students studenti = studentController.getStudents();

        assertThat(studenti.getStudentList().size()).isEqualTo(2);
    }
}
