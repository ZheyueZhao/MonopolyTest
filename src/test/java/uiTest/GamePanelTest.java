package uiTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import org.bihe.model.Person;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.stream.Stream;

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
    private StreetPanel[] streets = new StreetPanel[41];
    private StreetPanel mockStreetPanel;
    Piece mockPiece;
    @BeforeEach
    public void setUp() {
        gamePanel = spy(GamePanel.class);
        //gamePanel = new GamePanel();
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
        mockStreetPanel = mock(StreetPanel.class);
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

    static Stream<Object[]> locationProvider() {
        return Stream.of(
                new Object[]{10, true},  // First pair
                new Object[]{10, false},
                new Object[]{20, true},
                new Object[]{20, false},
                new Object[]{30, true},
                new Object[]{30, false},
                new Object[]{8, false},
                new Object[]{8, true},
                new Object[]{14, true},
                new Object[]{14, false},
                new Object[]{24, true},
                new Object[]{24, false}
        );
    }

    @Test
    public void testMovepieceOnePlace() throws NoSuchFieldException, IllegalAccessException {
        gamePanel = spy(GamePanel.class);
        mockPiece = new Piece(mock(Image.class),1,0,false);
        Field piecesField = GamePanel.class.getDeclaredField("pieces");
        piecesField.setAccessible(true);
        HashMap<Integer, Piece> pieces = new HashMap<>();
        HashMap<Integer,Piece> piecesMap = (HashMap<Integer, Piece>) piecesField.get(gamePanel);
        piecesMap.put(1,mockPiece);
        //Piece mockPiece = new Piece(mock(Image.class),0,0,false);
        //when(gamePanel.makePiece(anyInt(), anyInt())).thenReturn(null);
        gamePanel.panels[1].setSize(new Dimension(100, 100));
        streets[1] = mockStreetPanel;
        gamePanel.streets = streets;
        try(MockedConstruction<PieceWorker> mockedPieceWorker = mockConstruction(PieceWorker.class)) {
            gamePanel.movePieceOnePlace(1, 0);
            assert mockedPieceWorker.constructed().size() == 2;
        }
    }

    @ParameterizedTest
    @MethodSource("locationProvider")
    public void testMovepieceOnePlacePanel10(int panelNumber, boolean move) throws NoSuchFieldException, IllegalAccessException {
        gamePanel = spy(GamePanel.class);
        mockPiece = new Piece(mock(Image.class),1,panelNumber,move);
        Field piecesField = GamePanel.class.getDeclaredField("pieces");
        piecesField.setAccessible(true);
        HashMap<Integer, Piece> pieces = new HashMap<>();
        HashMap<Integer,Piece> piecesMap = (HashMap<Integer, Piece>) piecesField.get(gamePanel);
        piecesMap.put(panelNumber,mockPiece);
        gamePanel.panels[panelNumber+1].setSize(new Dimension(100, 100));
        streets[panelNumber+1] = mockStreetPanel;
        gamePanel.streets = streets;
        try(MockedConstruction<PieceWorker> mockedPieceWorker = mockConstruction(PieceWorker.class)) {
            gamePanel.movePieceOnePlace(panelNumber, 0);
            assert mockedPieceWorker.constructed().size() == 2;
        }
    }

}

