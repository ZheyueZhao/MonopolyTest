package modelTest.DAOTest;

import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.bihe.DAO.EstateDAO;
import org.bihe.model.Estate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void testGetEstatesHasCorrectDefaults() {
        testEstates = estateDao.getEstates();
        assert correctDefaults(testEstates);
    }

    @Test
    public void testGetOneEstate() {
        int index = rand.nextInt(estateKeys.length);
        Estate result = estateDao.getOneEstate(index);
        assertNotNull(result);
    }

    @Test
    public void testGetOneEstateByName() {
        int index = rand.nextInt(estateKeys.length);
        Estate result = estateDao.getOneEstate(index);
        Estate nameResult = estateDao.getOneEstateByName(result.getName());
        assert nameResult == result;
    }


}
