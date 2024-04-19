package uiTest;
import org.bihe.ui.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Image;
import java.awt.image.BufferedImage;


public class PieceTest {

    private Piece piece;

    @BeforeEach
    public void setUp() {
        // You may need to mock the Image object
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        int pieceNumber = 1;
        int panelNumber = 0;
        boolean move = true;
        piece = new Piece(image, pieceNumber, panelNumber, move);

    }

    @Test
    public void testSetAndGetImage() {
        // Set image using setter
        Image newImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        piece.setImage(newImage);

        // Get image using getter and assert equality
        Image retrievedImage = piece.getImage();
        assertEquals(newImage, retrievedImage);
    }

    @Test
    public void testSetAndGetPieceNumber() {
        // Set piece number using setter
        int retrievedPieceNumber = piece.getPieceNumber();
        assertEquals(1, retrievedPieceNumber);
    }

    @Test
    public void testGetMove() {
        // Set piece number using setter
        boolean move = piece.isMove();
        assertEquals(true, move);
    }

    @Test
    public void testSetX() {
        // Set piece number using setter
        piece.setX(5);
        assert piece.getX() == 5;
    }

    @Test
    public void testSetY() {
        // Set piece number using setter
        piece.setY(5);
        assert piece.getY() == 5;
    }

    @Test
    public void testSetAndGetPanelNumber() {
        // Set panel number using setter
        int newPanelNumber = 5;
        piece.setPanelNumber(newPanelNumber);

        // Get panel number using getter and assert equality
        int retrievedPanelNumber = piece.getPanelNumber();
        assertEquals(newPanelNumber, retrievedPanelNumber);
    }

    @Test
    public void testSetAndGetImageNotNull() {
        // Ensure that the image retrieved using getter is not null
        assertNotNull(piece.getImage());
    }

    @Test
    public void testMovePiece_PanelNumberLessThan11() {
        piece.movePiece();
        assertEquals(-1, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumber11() {
        piece.setPanelNumber(10);
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(-1, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumberLessThan21() {
        piece.setPanelNumber(15);
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(-1, piece.getY());
    }

    // Add more test methods to cover all cases of moving the piece...

    @Test
    public void testMovePiece_PanelNumber30() {
        piece.setPanelNumber(30);
        piece.setMove(true); // Setting move to true for testing
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(1, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumber30NoMove() {
        piece.setPanelNumber(30);
        piece.setMove(false); // Setting move to true for testing
        piece.movePiece();
        assertEquals(1, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumber10Move() {
        piece.setPanelNumber(10);
        piece.setMove(true); // Setting move to true for testing
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(-1, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumber0NoMove() {
        piece.setPanelNumber(0);
        piece.setMove(false); // Setting move to true for testing
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(1, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumber20Move() {
        piece.setPanelNumber(20);
        piece.setMove(true); // Setting move to true for testing
        piece.movePiece();
        assertEquals(1, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void testMovePiece_PanelNumberGreaterThan30() {
        piece.getX();
        piece.setPanelNumber(35);
        piece.setMove(false); // Setting move to false for testing
        piece.movePiece();
        assertEquals(0, piece.getX());
        assertEquals(1, piece.getY());
    }
}