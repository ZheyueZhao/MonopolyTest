package uiTest;

import org.bihe.ui.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GamePanelTest {

    private GamePanel gamePanel;
    private StreetPanel[] streets = new StreetPanel[41];
    private StreetPanel mockStreetPanel;
    Piece mockPiece;

    public void setUpHelper(){

    }
    @BeforeEach
    public void setUp() {
        gamePanel = spy(GamePanel.class);
        gamePanel.setPreferredSize(new Dimension(800, 600)); // Set preferred size for testing
        gamePanel.setLayout(new GridLayout());
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

    @Test
    public void testImageSetUp() throws NoSuchFieldException, IllegalAccessException {
        gamePanel = spy(GamePanel.class);
        for(int i=0;i<40;i++){
            gamePanel.panels[i].setSize(new Dimension(100, 100));
        }
        assertDoesNotThrow(() -> {
            gamePanel.addImageToPanels();
        });
    }

}

