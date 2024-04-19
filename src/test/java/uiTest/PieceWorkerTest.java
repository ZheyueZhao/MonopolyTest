package uiTest;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.Piece;
import org.bihe.ui.PieceWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PieceWorkerTest {
    private Piece piece;
    private Component component;
    @BeforeEach
    public void setUp() {
        // You may need to mock the Image object
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        int pieceNumber = 1;
        int panelNumber = 0;
        boolean move = true;
        piece = new Piece(image, pieceNumber, panelNumber, move);
        component = mock(Component.class);
    }

    @Test
    public void testSouthMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(true);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("southMove");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getX() == -3;
        verify(component, times(3)).repaint();


    }

    @Test
    public void testSouthNoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(false);
        piece.setX(3);
        piece.setPanelNumber(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("southMove");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getX() == 0;
        verify(component, times(3)).repaint();


    }

}
