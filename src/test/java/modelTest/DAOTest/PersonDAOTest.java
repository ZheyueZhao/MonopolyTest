package modelTest.DAOTest;



import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.util.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private static PersonDAO personDAO;
    private static Map<String, Person> testPersons;
    private static Person person;


    @BeforeAll
    public static void setUp() {

        personDAO = PersonDAO.getPersonDAO();
        personDAO.getPersons();
        testPersons = new HashMap<>();
        person = new Person("eve", "980428");
        personDAO.removePerson(person);
    }

    @Test
    public void testAddPerson() {
        assertTrue(personDAO.addPerson(person));
        testPersons = personDAO.getPersons();
        assert testPersons.containsKey("eve") == true;
    }




}