package uiTest;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.Piece;
import org.bihe.ui.PieceWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    public void testPanel10Move() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(true);
        piece.setY(3);
        piece.setPanelNumber(10);

        JPanel newPanel = new JPanel();

        newPanel.setPreferredSize(new Dimension(640, 480));

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            //gamePanel.addAllPieces();
            //gamePanel.getPanelSize(10);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("panelNo10Move");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

}
