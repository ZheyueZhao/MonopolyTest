package uiTest;

import org.bihe.ui.StreetPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StreetPanelTest {

    private StreetPanel streetPanel;

    @BeforeEach
    public void setUp() {
        Image backGround = null; // You need to provide a valid image here
        Image house1 = null; // You need to provide a valid image here
        Image house2 = null; // You need to provide a valid image here
        Image house3 = null; // You need to provide a valid image here
        Image house4 = null; // You need to provide a valid image here
        Image hotel = null; // You need to provide a valid image here
        streetPanel = new StreetPanel(1, backGround, house1, house2, house3, house4, hotel);
    }

    @Test
    public void testConstructor() {
        assertNotNull(streetPanel);
        assertEquals(1, streetPanel.getNumber());
    }

    @Test
    public void testSetNumber() {
        streetPanel.setNumber(2);
        assertEquals( 2, streetPanel.getNumber());
    }

}