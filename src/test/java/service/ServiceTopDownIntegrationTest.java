package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class ServiceTopDownIntegrationTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void addStudent_IntegrationTopDown_ValidId_Added() {
        Student student = new Student("12", "ValidName", 111);
        fileRepository1.save(student);
        Student studentInRepo = fileRepository1.findOne("12");
        Assert.assertEquals(studentInRepo.getID(), student.getID());
    }

    @Test
    public void addAssignment_IntegrationTopDown_Valid_Added() {
        this.service.saveStudent("13", "nume de student", 273);

        this.service.saveTema("qkifhiufhasfasf", "descriere", 8, 5);
    }

    @Test
    public void addGrade_IntegrationTopDown_Valid_Added() {
        this.service.saveStudent("14", "nume de student2", 233);

        this.service.saveTema("tema1", "descriere", 8, 5);

        this.service.saveNota("14", "tema1", 4, 7, "foarte prost");
    }
}
