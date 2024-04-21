package modelTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.model.Estate;
import org.bihe.ui.GUIManager;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PersonTest {
    private GUIManager guiManagerMock;
    private PersonDAO personDao;
    private Person person;
    @BeforeEach
    public void setUp() throws Exception {
       // guiManagerMock = mock(GUIManager.class);
        person = new Person("testUser", "password");
        personDao = PersonDAO.getPersonDAO();
        personDao.addPerson(person);
        personDao.setUserThatSignIn("testUser");

    }

    @Test
    public void testPerson() {
        // Create a sample person

        // Test getters and setters
        assertEquals("testUser", person.getUserName());
        assertEquals("password", person.getPassword());

        person.setUserName("newUser");
        person.setPassword("newPassword");

        assertEquals("newUser", person.getUserName());
        assertEquals("newPassword", person.getPassword());

        person.setLocation(5);
        assertEquals(5, person.getLocation());

        //GUIManager guiManagerMock = mock(GUIManager.class);
        //PlayerPanel playerPanelMock = mock(PlayerPanel.class);


        //when(guiManagerMock.getPlayerPanel()).thenReturn(playerPanelMock);
        person.setMoney(500);

        assertEquals(500, person.getMoney());

        ArrayList<Estate> estates = new ArrayList<>();
        estates.add(new ConcreteEstate("Estate1", 1, 200, 20, 100, 120));
        estates.add(new ConcreteEstate("Estate2", 2, 300, 30, 150, 180));

        person.setEstates(estates);
        assertEquals(estates, person.getEstates());

        person.setHaveJailCard(true);
        assertTrue(person.HaveJailCard());

        person.setYourTurn(true);
        assertTrue(person.isYourTurn());

        // Test methods
        assertEquals(10, person.newLocation(5)); // Testing newLocation method
        assertEquals(510, person.newMoney(10)); // Testing newMoney method
        assertEquals(0, person.getJail()); // Testing getJail method

        person.goToJail();
        assertEquals(3, person.getJail());

        person.oneRoundInJail();
        assertEquals(2, person.getJail());

        person.outOfJail();
        assertEquals(0, person.getJail());

        person.resetPair();
        assertFalse(person.isThreePair());
    }

    @Test
    public void testOtherPerson() {
        Person p = new Person("testUser", "password", 39, 100);

        p.setPieceNumber(2);
        assertEquals(2, p.getPieceNumber());


    }

    @Test
    public void testGo() {
        person = PersonDAO.getPersonDAO().getThePerson();

        int loc = (person.getLocation() + 45) % 40;
        int mon = person.getMoney() + 200;

        person.setLocation(person.newLocation(45));

        assertEquals(loc, person.getLocation());
        assertEquals(mon, person.getMoney());
    }

    @Test
    public void testBackwards() {
        int loc = (person.getLocation() - 10) + 40;
        person.setLocation(person.newLocation(-10));
        assertEquals(loc, person.getLocation());
    }

    @Test
    public void testThreePair() {
        person.resetPair();
        person.isThreePair();
        assertFalse(person.isThreePair());
        assertTrue(person.isThreePair());
        assertFalse(person.isThreePair());
    }

    @Test
    public void testAddRemoveEstates() {
        ArrayList<Estate> estates = new ArrayList<>();
        ConcreteEstate estate = new ConcreteEstate("Estate1", 1, 100, 10, 20, 30);
        ConcreteEstate other = new ConcreteEstate("Estate2", 2, 40, 20, 10, 30);

        estates.add(estate);

        person.setEstates(person.newEstates_add(estates));

        ArrayList<Estate> ests = person.getEstates();
        assertEquals(estate, ests.get(0));

        person.setEstates(person.newEstates_remove(estates));
        ests = person.getEstates();
        assertTrue(ests.isEmpty());

        person.setEstates(person.newEstates_add(estates));
        ests = person.getEstates();
        assertEquals(estate, ests.get(0));


    }
}
