package uiTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.bihe.ui.Piece;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.bihe.model.Person;
import org.bihe.DAO.PersonDAO;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import org.bihe.DAO.EstateDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class GamePanelTest {

    private GamePanel gamePanel;
    private static HashMap<Integer, Estate> testEstates;
    private Estate estate;
    private PersonDAO personDAO;
    private Person person;
    private Person person2;
    private EstateDAO estateDAO;
    private PlayerDAO playerDAO;
    private Client clientMock;
    private Server server;
    private StreetActions streetActions = new StreetActions();
    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(800, 600)); // Set preferred size for testing
        gamePanel.setLayout(new GridLayout());
        // guiManagerMock = mock(GUIManager.class);
        person = new Person("testUser", "password");
        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        estateDAO = EstateDAO.getEstateDAO();
    }
    @Test
    public void testConstructor() {
        assertNotNull(gamePanel);
    }


    @Test
    public void testPanelsize(){
        Dimension dim = new Dimension(0,0);
        assertEquals(gamePanel.getPanelSize(3),dim);
    }
    @Test
    public void testDistance() {
        int distance = gamePanel.distance(2, 5);
        assertEquals(3, distance);

        distance = gamePanel.distance(39, 1);
        assertEquals(2, distance);
    }
    @Test
    public void testMovepieceOnePlace() {
        Client sampleMock = mock(Client.class);
        person.setHaveJailCard(false);

        person.setMoney(3);
        person.setLocation(30);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        MockedStatic mockedClient = mockStatic(Client.class);
        MockedConstruction<GamePanel> mockedGamePanel = mockConstruction(GamePanel.class);
        MockedStatic mockedJoption = mockStatic(JOptionPane.class);
        mockedClient.when(Client::getClient).thenReturn(sampleMock);

        doNothing().when(sampleMock).sendObject(any());
        streetActions.action();
        GamePanel gamePanelMock = mockedGamePanel.constructed().get(0);
        verify(gamePanelMock).movePieceOnePlace(0, 0);
        mockedClient.verify(Client::getClient);
        verify(sampleMock).sendObject(any());
        
    }
}

