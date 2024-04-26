package uiTest.actionPanelTest;

import org.bihe.ui.Piece;
import org.bihe.ui.actionPanel.DicePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DicePanelTest {
    DicePanel dicePanel;
    @BeforeEach
    public void setUp() {
        // You may need to mock the Image object
        dicePanel = new DicePanel();

    }

    @Test
    public void randomDistributionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> pieceWorkerTestClass = dicePanel.getClass();
        Method makeRandomMethod = pieceWorkerTestClass.getDeclaredMethod("makeRandom");
        makeRandomMethod.setAccessible(true);
        makeRandomMethod.invoke(dicePanel);


        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < 30000; i++) {

            int randomNumber = (int) makeRandomMethod.invoke(dicePanel);
            frequencyMap.put(randomNumber, frequencyMap.getOrDefault(randomNumber, 0) + 1);
        }

        for (int number = 1; number < 7; number++) {
            int frequency = frequencyMap.getOrDefault(number, 0);
            for(int num=1; num<7;num++){
                int alterFrequency = frequencyMap.getOrDefault(number, 0);
                assert frequency <= alterFrequency*1.5 && frequency >= alterFrequency*0.5;
            }

        }

    }

    @Test
    public void tossDiceTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> pieceWorkerTestClass = dicePanel.getClass();
        Method makeRandomMethod = pieceWorkerTestClass.getDeclaredMethod("tossDice");
        makeRandomMethod.setAccessible(true);


        assertDoesNotThrow(() -> {
            makeRandomMethod.invoke(dicePanel);
        });

    }

    @Test
    public void inJailTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> pieceWorkerTestClass = dicePanel.getClass();
        Method makeRandomMethod = pieceWorkerTestClass.getDeclaredMethod("inJail");
        makeRandomMethod.setAccessible(true);


        assertDoesNotThrow(() -> {
            makeRandomMethod.invoke(dicePanel);
        });

    }
}
