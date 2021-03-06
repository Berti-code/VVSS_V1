package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.*;

public class ServiceTestAddAssignment{
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    private final Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    private void resetRepository() {
        if(!fileRepository2.findAll().iterator().hasNext())
            return;
        for(Tema x: fileRepository2.findAll()) {
            fileRepository2.delete(x.getID());
        }
    }

    @Test
    public void addAssignment_UniqueId_Added() {
        resetRepository();
        Assert.assertEquals(1, this.service.saveTema("tema1", "tema de laborator", 7, 4));
    }

    @Test
    public void addAssignment_ExistingId_NotAdded() {
        Assert.assertEquals(1, this.service.saveTema("tema1", "tema de lab", 3, 5));
    }

    @Test
    public void addAssignment_EmptyId_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema("", "tema lab", 6, 4);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_NullId_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema(null, "tema lab", 6, 4);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_EmptyDescription_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema("2", "", 6, 4);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_NullDescription_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema("3", null, 6, 4);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_InvalidDeadline_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema("4", "description", 0, -1);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_InvalidStartline_NotAdded() {
        Iterable<Tema> elems = fileRepository2.findAll();
        try {
            Tema tema = new Tema("5", "description", 0, 0);
            fileRepository2.save(tema);
        } catch(ValidationException e) {
            Assert.fail();
        }
        Iterable<Tema> elemsNew = fileRepository2.findAll();
        Assert.assertEquals(IterableUtils.size(elems), IterableUtils.size(elemsNew));
    }

    @Test
    public void addAssignment_ValidEntity_Added() {
        Tema tema = new Tema("6", "desc", 6, 4);
        fileRepository2.save(tema);
        Tema elem = fileRepository2.findOne("6");
        Assert.assertEquals(elem.getID(), tema.getID());
    }
}
