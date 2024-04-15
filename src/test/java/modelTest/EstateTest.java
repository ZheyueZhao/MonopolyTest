package modelTest;
import org.bihe.model.Estate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstateTest {

    @Test
    public void testEstate() {
        // Create a sample estate
        Estate estate = new ConcreteEstate("Test Estate", 1, 200, 20, 100, 120);

        // Test getters
        assertEquals("Test Estate", estate.getName());
        assertEquals(1, estate.getPanelNo());
        assertEquals(200, estate.getPrice());
        assertEquals(20, estate.getRent());
        assertEquals(100, estate.getMortgage());
        assertEquals(120, estate.getUnMortgage());

        // Test setters
        estate.setName("New Estate");
        estate.setPanelNo(2);
        estate.setPrice(300);
        estate.setRent(30);
        estate.setMortgage(150);
        estate.setUnMortgage(180);

        assertEquals("New Estate", estate.getName());
        assertEquals(2, estate.getPanelNo());
        assertEquals(300, estate.getPrice());
        assertEquals(30, estate.getRent());
        assertEquals(150, estate.getMortgage());
        assertEquals(180, estate.getUnMortgage());

        // Test isMortgage
        assertFalse(estate.isMortgage());
        estate.setMortgage(true);
        assertTrue(estate.isMortgage());

        // Test isOwned
        assertFalse(estate.isOwned());
        estate.setOwned(true);
        assertTrue(estate.isOwned());

        // Test owner
        estate.setOwner("John");
        assertEquals("John", estate.getOwner());

        // Test toString
        assertEquals("New Estate  (mortgaged)", estate.toString());
        estate.setMortgage(false);
        assertEquals("New Estate", estate.toString());
    }
}