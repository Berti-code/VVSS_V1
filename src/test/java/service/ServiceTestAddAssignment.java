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

public class ServiceTestAddAssignment{
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    private final Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void addAssignment_UniqueId_Passed() {
        Assert.assertEquals(this.service.saveTema("tema1", "tema de laborator", 4, 7), 1);
    }

    @Test
    public void addAssignment_ExistingId_Failed() {
        Assert.assertEquals(this.service.saveTema("tema1", "tema de lab", 3, 5), 1);
    }
}
