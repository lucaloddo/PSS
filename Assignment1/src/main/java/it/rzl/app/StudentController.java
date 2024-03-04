package it.rzl.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/all", produces = "application/json")
    public Students getStudents() {
        Students response = new Students();
        ArrayList<Student> list = new ArrayList<>();
        studentRepository.findAll().forEach(list::add);
        response.setStudentList(list);
        return response;
    }

}