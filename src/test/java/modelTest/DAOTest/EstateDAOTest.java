package modelTest.DAOTest;

import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.junit.jupiter.api.*;
import org.bihe.DAO.EstateDAO;
import org.bihe.model.Estate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EstateDAOTest {
    private static EstateDAO estateDao;
    private static Map<Integer, Estate> testEstates;
    private static Estate estate;

    private static final Integer[] estateKeys = {1, 3, 5, 6, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 21, 23, 24, 25, 26, 27, 28, 29, 31, 32, 34, 35, 37, 39};
    private static final Integer[] nonEstateKeys = {2, 4, 7, 10, 17, 20, 22, 30, 33, 36, 38, 40};

    private Random rand = new Random();

    @BeforeEach
    public void setUp() {
        estateDao = EstateDAO.getEstateDAO();
    }

    @Test
    @Order(1)
    public void testGetEstatesNotEmpty() {
        testEstates = estateDao.getEstates();
        assertFalse(testEstates.isEmpty());
    }

    public boolean correctDefaults(Map<Integer, Estate> estates) {
        for (Map.Entry<Integer, Estate> E : estates.entrySet()) {
            if (!Arrays.asList(estateKeys).contains(E.getKey())) {
                return false;
            }

            if (Arrays.asList(nonEstateKeys).contains(E.getKey())) {
                return true;
            }
        }

        return true;
    }

    @Test
    @Order(2)
    public void testGetEstatesHasCorrectDefaults() {
        testEstates = estateDao.getEstates();
        assert correctDefaults(testEstates);
    }

    @Test
    @Order(3)
    public void testGetOneEstate() {
        int index = rand.nextInt(estateKeys.length);
        Estate result = estateDao.getOneEstate(estateKeys[index]);
        assertNotNull(result);
    }

    @Test
    @Order(4)
    public void testGetOneEstate_NotFound() {
        int index = rand.nextInt(1, estateKeys.length);
        Estate result = estateDao.getOneEstate(40 + index);
        assertNull(result);
    }

    @Test
    @Order(5)
    public void testGetOneEstateByName() {
        Estate retrieved = estateDao.getOneEstateByName("WaterWork");
        assertNotNull(retrieved);
        assertEquals("WaterWork", retrieved.getName());
    }

    @Test
    @Order(6)
    public void testGetOneEstateByName_NotFound() {
        Estate retrieved = estateDao.getOneEstateByName("NonExistent");
        assertNull(retrieved);
    }

    @Test
    @Order(7)
    public void testChangeEstateDAO() {
        HashMap<Integer, Estate> newEstates = new HashMap<>();
        Street street = new Street("NewStreet", 777, 100, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110);
        newEstates.put(777, street);
        estateDao.changeEstateDAO(newEstates);
        Estate retrieved = estateDao.getOneEstate(777);
        assertNotNull(retrieved);
        assertEquals("NewStreet", retrieved.getName());

    }


}
