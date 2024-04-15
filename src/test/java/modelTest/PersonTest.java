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
        guiManagerMock = mock(GUIManager.class);
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

        GUIManager guiManagerMock = mock(GUIManager.class);
        PlayerPanel playerPanelMock = mock(PlayerPanel.class);


        //when(guiManagerMock.getPlayerPanel()).thenReturn(playerPanelMock);
        person.setMoney(500);

        assertEquals(500, person.getMoney());
    }


    }
