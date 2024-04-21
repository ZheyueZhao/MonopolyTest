package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Data;
import org.bihe.model.Estate;
import org.bihe.model.Person;

import org.bihe.ui.GUIManager;
import org.bihe.ui.GameFrame;
import org.bihe.ui.GamePanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class ConcreteEstate extends Estate {
    public ConcreteEstate(String name, int panelNo, int price, int rent, int mortgage, int unMortgage) {
        super(name, panelNo, price, rent, mortgage, unMortgage);
    }
    // Implement constructors and methods of ConcreteEstate
}
public class DataTest {

    private HashMap<String, Person> players;
    private HashMap<Integer, Estate> estates;
    private Data testData;


    @BeforeEach
    public void setUp() {
        // Create sample players
        players = new HashMap<>();
        players.put("Player1", new Person("john","980428",2,29));
        players.put("Player2", new Person("josh","980428",3,40));


        estates = new HashMap<>();
        estates.put(1, new ConcreteEstate("estate1", 2, 5,2,3,8));
        estates.put(2, new ConcreteEstate("estate2", 3, 6,2,4,8));

        PersonDAO.getPersonDAO().getPersons();
        PersonDAO.getPersonDAO().setUserThatSignIn("testUser");

        // Create test data
        testData = new Data(players, estates, 1, 0, 5);
    }

    @Test
    public void testConstructorWithMove() {
        assert testData.isPlayerMove() == true;
        assertEquals(players, testData.getPlayers());
        assertEquals(estates, testData.getEstates());
        assertEquals(1, testData.getPieceNumber());
        assertEquals(0, testData.getLocation());
        assertEquals(5, testData.getNewLocation());
    }

    @Test
    public void testConstructorWithoutMove() {
        Data testDataWithoutMove = new Data(players, estates);
        assert testDataWithoutMove.isPlayerMove() == false;
        assertEquals(players, testDataWithoutMove.getPlayers());
        assertEquals(estates, testDataWithoutMove.getEstates());
    }

    @Test
    public void testAnalysDataWithMove() {
        GamePanel panel = mock(GamePanel.class);
        EstatesPanel estates = mock(EstatesPanel.class);
        PlayerPanel players = mock(PlayerPanel.class);
        GameFrame frame = mock(GameFrame.class);

        try(MockedStatic<GUIManager> mockedGUIManager = mockStatic(GUIManager.class)){
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(panel);
            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(players);
            mockedGUIManager.when(GUIManager::getGameFrame).thenReturn(frame);
            mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(estates);

            testData.analysData();
            mockedGUIManager.verify(GUIManager::getGamePanel, times(1));
            mockedGUIManager.verify(GUIManager::getEstatePanel, times(1));
            mockedGUIManager.verify(GUIManager::getPlayerPanel, times(1));
            mockedGUIManager.verify(GUIManager::getGameFrame, times(1));
        }
    }

    @Test
    public void testAnalysDataWithoutMove() {
        Data data = new Data(players, estates);

        EstatesPanel estates = mock(EstatesPanel.class);
        PlayerPanel players = mock(PlayerPanel.class);
        GameFrame frame = mock(GameFrame.class);

        try(MockedStatic<GUIManager> mockedGUIManager = mockStatic(GUIManager.class)){
            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(players);
            mockedGUIManager.when(GUIManager::getGameFrame).thenReturn(frame);
            mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(estates);

            data.analysData();
            mockedGUIManager.verify(GUIManager::getEstatePanel, times(1));
            mockedGUIManager.verify(GUIManager::getPlayerPanel, times(1));
            mockedGUIManager.verify(GUIManager::getGameFrame, times(1));
        }
    }

    @Test
    public void testMismatchPieceNumbers() {
        PersonDAO.getPersonDAO().getThePerson().setPieceNumber(1);
        EstatesPanel estates = mock(EstatesPanel.class);
        PlayerPanel players = mock(PlayerPanel.class);
        GameFrame frame = mock(GameFrame.class);

        try(MockedStatic<GUIManager> mockedGUIManager = mockStatic(GUIManager.class)){
            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(players);
            mockedGUIManager.when(GUIManager::getGameFrame).thenReturn(frame);
            mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(estates);

            testData.analysData();
            mockedGUIManager.verify(GUIManager::getEstatePanel, times(1));
            mockedGUIManager.verify(GUIManager::getPlayerPanel, times(1));
            mockedGUIManager.verify(GUIManager::getGameFrame, times(1));
        }

        PersonDAO.getPersonDAO().getThePerson().setPieceNumber(0);
    }
}