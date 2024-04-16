package modelTest.DAOTest;



import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.bihe.util.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private static PersonDAO personDAO;
    private static Map<String, Person> testPersons;
    private static Map<String, Person> testPlayers;
    private static Person person;
    private PlayerDAO playerDao;


    @BeforeEach
    public void setUp() {

        personDAO = PersonDAO.getPersonDAO();
        personDAO.getPersons();
        testPersons = new HashMap<>();
        person = new Person("eve", "980428");

    }

    @Test
    public void testRemovePerson() {
        personDAO.removePerson(person);
        testPersons = personDAO.getPersons();
        assert testPersons.containsKey("eve") == false;
    }
    @Test
    public void testAddPerson() {
        personDAO.removePerson(person);
        assertTrue(personDAO.addPerson(person));
        testPersons = personDAO.getPersons();
        assert testPersons.containsKey("eve") == true;
    }

    @Test
    public void testSetSignin(){
        personDAO.setUserThatSignIn("eve");
        String testPerson = personDAO.getUserThatSignIn();
        assert testPerson == "eve";
    }

    @Test
    public void testGetThePerson(){
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("eve");
        Person testPerson = personDAO.getThePerson();
        assert testPerson.getUserName().equals("eve");
    }

    @Test
    public void testGetPersons(){
        testPersons = personDAO.getPersons();
        assert testPersons.containsKey("eve") == true;

    }

    @Test
    public void testCheckForPersons(){

        assert personDAO.checkForPerson("testUser","password");

    }

    @Test
    public void testChangePersonForPlayerDao(){
        testPlayers = PlayerDAO.getPlayerDAO().getPlayers();
        personDAO.setUserThatSignIn("eve");
        person.setPassword("12345");
        personDAO.changePerson(person);
        testPersons = personDAO.getPersons();
        Person newPerson = testPersons.get("eve");
        assert newPerson.getPassword().equals("12345");
    }





}