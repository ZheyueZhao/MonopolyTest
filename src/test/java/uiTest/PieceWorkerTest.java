package uiTest;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.Piece;
import org.bihe.ui.PieceWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        piece.setPanelNumber(3);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
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
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getX() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testEastMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(true);
        piece.setY(3);
        piece.setPanelNumber(16);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testEastNoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(false);
        piece.setY(3);
        piece.setPanelNumber(16);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testNorthMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(true);
        piece.setX(0);
        piece.setPanelNumber(26);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getX() == 3;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testWestMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(true);
        piece.setY(-3);
        piece.setPanelNumber(36);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testWestNoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(false);
        piece.setY(-3);
        piece.setPanelNumber(36);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
        southMoveMethod.setAccessible(true);
        southMoveMethod.invoke(pieceWorkerTest);

        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testNorthNoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        piece.setMove(false);
        piece.setX(-3);
        piece.setPanelNumber(26);
        when(component.getWidth()).thenReturn(3);
        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
        Method southMoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
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

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanelNoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(false);
        piece.setX(3);
        piece.setPanelNumber(10);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getX() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel20Move() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(true);
        piece.setX(-3);
        piece.setPanelNumber(20);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getX() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel20NoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(false);
        piece.setY(3);
        piece.setPanelNumber(20);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel30Move() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(true);
        piece.setY(-3);
        piece.setPanelNumber(30);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel30NoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(false);
        piece.setX(-3);
        piece.setPanelNumber(30);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getX() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel0Move() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(true);
        piece.setX(3);
        piece.setPanelNumber(0);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getX() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testPanel0NoMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        piece.setMove(false);
        piece.setY(-3);
        piece.setPanelNumber(0);

        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {

            Dimension dm = new Dimension(640, 400);
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            when(gamePanel.getPanelSize(anyInt())).thenReturn(dm);
            PieceWorker pieceWorkerTest = new PieceWorker(component, piece, 2, true);
            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method panelNo10MoveMethod = pieceWorkerTestClass.getDeclaredMethod("doInBackground");
            panelNo10MoveMethod.setAccessible(true);
            panelNo10MoveMethod.invoke(pieceWorkerTest);
        }
        assert piece.getY() == 0;
        verify(component, times(3)).repaint();
    }

    @Test
    public void testDone() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        PieceWorker pieceWorkerTest = new PieceWorker(component, piece,2,true);
        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)){
            GamePanel gamePanel = mock(GamePanel.class);
            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanel);

            Class<?> pieceWorkerTestClass = pieceWorkerTest.getClass();
            Method doneMethod = pieceWorkerTestClass.getDeclaredMethod("done");
            doneMethod.setAccessible(true);
            doneMethod.invoke(pieceWorkerTest);

            mockedGUIManager.verify(GUIManager::getGamePanel);
        }
    }
}
