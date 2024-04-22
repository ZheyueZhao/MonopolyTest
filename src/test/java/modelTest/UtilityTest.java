package modelTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.bihe.DAO.EstateDAO;
import org.bihe.model.Estate;
import org.bihe.model.Street;
import org.bihe.model.Utility;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;


public class UtilityTest {

    private Utility utility;
    private EstateDAO estateDAO;
    private Estate estate;
    @BeforeEach
    public void setUp() {
        // Mocking EstateDAO
        estateDAO = new EstateDAO();
        estate = estateDAO.getEstates().get(12);
        estate.setOwner("eve");
        estateDAO.changeEstate(estate);
        estate = estateDAO.getEstates().get(28);
        estate.setOwner("eve");
        estateDAO.changeEstate(estate);

        utility = new Utility("TestUtility", 99, 100, 10, 20, 30, 40);
        //estate.setOwner("eve");
        utility.setOwner("eve");
    }

    @Test
    public void testRent_CountIsZero() {
        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(estateDAO);
            assertEquals(40, utility.rent());
        }
    }

    @Test
    public void testRent_CountIsOne() {
        // Mocking owner of ElectricCompany
        utility.setOwner("TestOwner");
        estate = estateDAO.getEstates().get(12);
        estate.setOwner("TestOwner");
        estateDAO.changeEstate(estate);
        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(estateDAO);
            assertEquals(10, utility.rent());
        }
    }

    @Test
    public void testRent_CountIsOne_Other() {
        // Mocking owner of ElectricCompany
        utility.setOwner("TestOwner");
        estate = estateDAO.getEstates().get(28);
        estate.setOwner("TestOwner");
        estateDAO.changeEstate(estate);
        estate = estateDAO.getEstates().get(12);
        estate.setOwner("TestUser");
        estateDAO.changeEstate(estate);
        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(estateDAO);
            assertEquals(10, utility.rent());
        }
    }

    @Test
    public void testRent_CountIsTwo() {
        // Mocking owner of ElectricCompany and WaterWork
        utility.setOwner("TestOwner");
        utility.setOwner("TestOwner");
        estate = estateDAO.getEstates().get(28);
        estate.setOwner("TestOwner");
        estateDAO.changeEstate(estate);
        estate = estateDAO.getEstates().get(12);
        estate.setOwner("TestOwner");
        estateDAO.changeEstate(estate);
        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(estateDAO);
            assertEquals(40, utility.rent());
        }
    }

    @Test
    public void testOtherConstructorAndRentForTwoModifiers() {
        Utility newUtil = new Utility("Util", 1, 10, 10, 10, 10);
        newUtil.setRentForTwo(30);
        assertEquals(30, newUtil.getRentForTwo());
    }
}