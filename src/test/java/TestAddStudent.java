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

public class TestAddStudent {
    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository fileRepository1;
    private TemaXMLRepository fileRepository2;
    private NotaXMLRepository fileRepository3;

    private Service service;

    TestAddStudent() {
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.notaValidator = new NotaValidator();

        this.fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        this.fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        this.fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    // id si grupa 110, 111, 109, 939, 938, 937
    // id - valid, null, empty string
    // nume - valid, null, empty string
    // grupa - 111, 110, 109
    // grupa - 939, 938, 937

    @Test
    public void testAddStudent_InvalidGroup110_DoesntPass() {
        Assertions.assertEquals(this.service.saveStudent("student1", "George1", 110), 1);
    }

    @Test
    public void testAddStudent_ValidGroup111_Passes() {
        Assertions.assertEquals(this.service.saveStudent("student2", "George2", 111), 0);
    }

    @Test
    public void testAddStudent_InvalidGroup109_DoesntPass() {
        Assertions.assertEquals(this.service.saveStudent("student3", "George3", 109), 1);
    }

    @Test
    public void testAddStudent_InvalidGroup939_DoesntPass() {
        Assertions.assertEquals(this.service.saveStudent("student4", "George4", 939),1);
    }

    @Test
    public void testAddStudent_ValidGroup938_Passes() {
        Assertions.assertEquals(this.service.saveStudent("student5", "George5", 938), 1);
    }
}
