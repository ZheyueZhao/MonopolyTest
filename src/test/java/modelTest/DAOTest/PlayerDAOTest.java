package modelTest.DAOTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDAOTest {
    private static PlayerDAO playerDAO;
    private static PersonDAO personDAO;
    private static Person newPerson;

    @BeforeEach
    public void setup() {
        playerDAO = PlayerDAO.getPlayerDAO();
        personDAO = PersonDAO.getPersonDAO();
        newPerson = new Person("test", "password");
        personDAO.addPerson(newPerson);
    }

    @Test
    public void testAddPlayer() {
        assertTrue(playerDAO.addPlayer(newPerson));
    }

    @Test
    public void testGetOnePlayer() {
        playerDAO.addPlayer(newPerson);
        Person p = playerDAO.getOnePlayer("test");
        assertNotNull(p);
        assertEquals("test", p.getUserName());
    }

    @Test
    public void testChangePlayerDAO() {
        HashMap<String, Person> players = new HashMap<String, Person>();
        players.put("test", newPerson);
        personDAO.setUserThatSignIn("test");
        playerDAO.changePlayerDAO(players);
        HashMap<String, Person> retrieved = playerDAO.getPlayers();

        for (Map.Entry<String, Person> E : players.entrySet()) {
            assertTrue(players.containsKey(E.getKey()));
            assertEquals(players.get(E.getKey()), E.getValue());
        }
    }

    @Test
    public void testChangePlayerNotActiveUser() {
        playerDAO.addPlayer(newPerson);
        Person p = playerDAO.getOnePlayer("test");
        String initP = p.getPassword();
        newPerson.setPassword("new password");
        personDAO.setUserThatSignIn("eve");
        playerDAO.changeOnePlayer(newPerson);
        Person newP = playerDAO.getOnePlayer("test");

        assertEquals("password", initP);
        assertEquals("new password", newP.getPassword());
    }

    @Test
    public void testChangePlayerDAOActiveUserNotInList() {
        HashMap<String, Person> players = new HashMap<String, Person>();
        players.put("test", newPerson);
        personDAO.setUserThatSignIn("eve");
        playerDAO.changePlayerDAO(players);
        HashMap<String, Person> retrieved = playerDAO.getPlayers();

        for (Map.Entry<String, Person> E : players.entrySet()) {
            assertTrue(players.containsKey(E.getKey()));
            assertEquals(players.get(E.getKey()), E.getValue());
        }
    }

    @Test
    public void testToString() {
        assertEquals("", playerDAO.toString());
    }
}
