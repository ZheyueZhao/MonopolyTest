package uiTest;
import org.bihe.ui.SendRequestFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.*;

import javax.swing.JFrame;


public class SendRequestFrameTest {

    @Test
    public void testSendRequestFrame() {
        SendRequestFrame frame = new SendRequestFrame();
        assertNotNull(frame);
        assertEquals(JFrame.DO_NOTHING_ON_CLOSE, frame.getDefaultCloseOperation());
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int expectedWidth = screenSize.width / 2;
        int expectedHeight = (screenSize.height - 55) * 2 / 3;
        assertEquals(expectedWidth, frame.getWidth());
        assertEquals(expectedHeight, frame.getHeight());
        assertEquals(true, frame.isVisible());
    }
}