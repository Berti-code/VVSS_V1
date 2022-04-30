package service;

import domain.Nota;
import domain.Pair;
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

public class ServiceIntegrationTests {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    @Test
    public void addStudentIntegration_ValidId_Added() {
        Student student = new Student("12", "ValidName", 111);
        fileRepository1.save(student);
        Student studentInRepo = fileRepository1.findOne("12");
        Assert.assertEquals(studentInRepo.getID(), student.getID());
    }

    @Test
    public void addAssignmentIntegration_ValidEntity_Added() {
        Tema tema = new Tema("9", "desc", 6, 4);
        fileRepository2.save(tema);
        Tema elem = fileRepository2.findOne("9");
        Assert.assertEquals(elem.getID(), tema.getID());
    }

    @Test
    public void addGradeIntegration_ValidId_Added() {
        Nota nota = new Nota(new Pair("12", "9"), 10, 7, "a fost bine");
        fileRepository3.save(nota);
        Nota notaInRepo = fileRepository3.findOne(new Pair("12", "9"));
        Assert.assertEquals(notaInRepo.getID(), nota.getID());
    }

    @Test
    public void addStudentAssignmentGradeIntegration() {
        Student student = new Student("14", "ValidName", 111);
        fileRepository1.save(student);

        Tema tema = new Tema("11", "desc", 6, 4);
        fileRepository2.save(tema);

        Nota nota = new Nota(new Pair("14", "11"), 10, 7, "a fost bine");
        fileRepository3.save(nota);
        Nota notaInRepo = fileRepository3.findOne(new Pair("14", "11"));
        Assert.assertEquals(notaInRepo.getID(), nota.getID());
    }
}
