package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;
import org.junit.jupiter.api.Assertions;

public class ServiceTest {

    private final Service service;

    ServiceTest() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudent_InvalidGroup110_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("student1", "George1", 110));
    }

    @Test
    public void testAddStudent_ValidGroup111_Passes() {
        Assertions.assertEquals(0, this.service.saveStudent("student2", "George2", 111));
    }

    @Test
    public void testAddStudent_InvalidGroup109_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("student3", "George3", 109));
    }

    @Test
    public void testAddStudent_InvalidGroup939_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("student4", "George4", 939));
    }

    @Test
    public void testAddStudent_ValidGroup938_Passes() {
        Assertions.assertEquals(1, this.service.saveStudent("student5", "George5", 938));
    }

    @Test
    public void TestAddStudent_ValidName_Passes() {
        Assertions.assertEquals(0, this.service.saveStudent("11", "ValidName", 111));
    }

    @Test
    public void TestAddStudent_EmptyName_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("12", "", 937));
    }

    @Test
    public void TestAddStudent_Null_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("13", null, 937));
    }

    @Test
    public void TestAddStudent_ID_Passes() {
        Assertions.assertEquals(0, this.service.saveStudent("11", "ValidName", 111));
    }

    @Test
    public void TestAddStudent_EmptyID_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent("", "ValidName", 937));
    }

    @Test
    public void TestAddStudent_NullID_Fails() {
        Assertions.assertEquals(1, this.service.saveStudent(null, "ValidName", 937));
    }
}
