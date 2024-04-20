package uiTest;

import org.bihe.ui.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GamePanelTest {

    private GamePanel gamePanel;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(800, 600)); // Set preferred size for testing
        gamePanel.setLayout(new GridLayout());
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
}

