package modelTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.RailRoad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RailRoadTest {

    private RailRoad railRoad;
    private Estate estate;
    private EstateDAO estateDao;

    @BeforeEach
    public void setUp() {
        railRoad = new RailRoad("Railroad", 1, 200, 25, 100, 120, 50, 100, 200);
        estateDao = EstateDAO.getEstateDAO();
        estate = estateDao.getOneEstate(5);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);
        estate = estateDao.getOneEstate(15);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);
        estate = estateDao.getOneEstate(25);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);
        estate = estateDao.getOneEstate(35);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Railroad", railRoad.getName());
        assertEquals(1, railRoad.getPanelNo());
        assertEquals(200, railRoad.getPrice());
        assertEquals(25, railRoad.getRent());
        assertEquals(100, railRoad.getMortgage());
        assertEquals(120, railRoad.getUnMortgage());
        assertEquals(50, railRoad.getRentForTwo());
        assertEquals(100, railRoad.getRentForThree());
        assertEquals(200, railRoad.getRentForfour());
    }

    @Test
    public void testRent() {
        // Mock EstateDAO
        EstateDAO estateDAO = mock(EstateDAO.class);
        HashMap<Integer, Estate> estates = new HashMap<>();
        //Estate estate = new ConcreteEstate("Estate1", 1, 200, 20, 100, 120);
        railRoad.setOwner("Owner");
        estates.put(5, railRoad);
        //estates.put(15, new ConcreteEstate("Estate2", 15, 150, 15, 75, 90));
        // estates.put(25, new ConcreteEstate("Estate3", 25, 200, 20, 100, 120));
        //estates.put(35, new ConcreteEstate("Estate4", 35, 250, 25, 125, 150));
        when(estateDAO.getEstates()).thenReturn(estates);
        // Set mocked EstateDAO to the RailRoad class
        System.out.println(estates.get(5).getOwner());
        // Test rent calculation with different number of owned railroads
        assertEquals(200, railRoad.rent()); // No owned railroads


    }
}