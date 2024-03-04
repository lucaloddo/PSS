package it.rzl.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTests {
    @Autowired
    private StudentController studentController;

    @Test
    public void integrationTest() {
        List<Student> studentsList = new ArrayList<>();

        Student student1 = new Student(844529, "Luca", "Loddo", "Informatica");
        Student student2 = new Student(844760, "Davide", "Zangari", "Sociologia");
        Student student3 = new Student(847381, "Matteo", "Rondena", "Criminologia");
        Student student4 = new Student(846982, "Valentina", "Rossi", "Scienze della formazione primaria");

        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);

        assertThat(studentsList.size()).isEqualTo(studentController.getStudents().getStudentList().size());
    }

}
