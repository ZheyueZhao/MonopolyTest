package modelTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.model.Estate;
import org.bihe.model.RailRoad;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RailRoadTest {

    private RailRoad railRoad;
    private Estate estate;
    private EstateDAO estateDao;


    @Test
    public void testConstructorAndGetters() {
        railRoad = new RailRoad("Railroad", 1, 200, 25, 100, 120, 50, 100, 200);

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
    public void testOtherConstructorAndSetters() {
        railRoad = new RailRoad("Railroad", 3, 200, 50, 100, 150);
        railRoad.setRentForTwo(50);
        railRoad.setRentForThree(100);
        railRoad.setRentForfour(150);

        assertEquals(50, railRoad.getRentForTwo());
        assertEquals(100, railRoad.getRentForThree());
        assertEquals(150, railRoad.getRentForfour());
    }

    @Test
    public void testRent() {
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

    @Test
    public void testRentOwnsThree() {
        railRoad = new RailRoad("Railroad", 1, 200, 25, 100, 120, 50, 100, 200);

        estateDao = EstateDAO.getEstateDAO();

        estate = estateDao.getOneEstate(5);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(15);
        estate.setOwner("NotOwner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(25);
        estate.setOwner("NotOwner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(35);
        estate.setOwner("NotOwner");
        estateDao.changeEstate(estate);

        EstateDAO estateDAO = mock(EstateDAO.class);
        HashMap<Integer, Estate> estates = new HashMap<>();
        railRoad.setOwner("NotOwner");
        estates.put(15, railRoad);
        when(estateDAO.getEstates()).thenReturn(estates);


        assertEquals(100, railRoad.rent()); // Three owned railroads
    }

    @Test
    public void testRentOwnsTwo() {
        railRoad = new RailRoad("Railroad", 1, 200, 25, 100, 120, 50, 100, 200);

        estateDao = EstateDAO.getEstateDAO();

        estate = estateDao.getOneEstate(5);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(15);
        estate.setOwner("NotOwner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(25);
        estate.setOwner("Owner");
        estateDao.changeEstate(estate);

        estate = estateDao.getOneEstate(35);
        estate.setOwner("NotOwner");
        estateDao.changeEstate(estate);

        EstateDAO estateDAO = mock(EstateDAO.class);
        HashMap<Integer, Estate> estates = new HashMap<>();
        railRoad.setOwner("NotOwner");
        estates.put(15, railRoad);
        when(estateDAO.getEstates()).thenReturn(estates);


        assertEquals(50, railRoad.rent()); // Three owned railroads
    }

    @Test
    public void testRentOwnsOne() {
        railRoad = new RailRoad("Railroad", 1, 200, 25, 100, 120, 50, 100, 200);

        estateDao = EstateDAO.getEstateDAO();

        estate = estateDao.getOneEstate(5);
        estate.setOwner("NotOwner");
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

        EstateDAO estateDAO = mock(EstateDAO.class);
        HashMap<Integer, Estate> estates = new HashMap<>();
        railRoad.setOwner("NotOwner");
        estates.put(5, railRoad);
        when(estateDAO.getEstates()).thenReturn(estates);


        assertEquals(25, railRoad.rent()); // One owned railroads
    }
}