import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class TestStudent {
    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository fileRepository1;
    private TemaXMLRepository fileRepository2;
    private NotaXMLRepository fileRepository3;

    private Service service;

    TestStudent(){
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.notaValidator = new NotaValidator();

        this.fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        this.fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        this.fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }
    // id - valid, null, empty string
    // nume - valid, null, empty string
    // grupa - 111, 110, 109
    // grupa - 939, 938, 937
    @Test
    public void TestAddStudent_ValidName_Passes(){
         Assertions.assertEquals(0, this.service.saveStudent("23", "ValidName", 937));
    }
    public void TestAddStudent_EmptyString_Fails(){
        Assertions.assertEquals(1, this.service.saveStudent("23", "", 937));
    }
    public void TestAddStudent_Null_Fails(){
        Assertions.assertEquals(1, this.service.saveStudent("23", null, 937));
    }
}
