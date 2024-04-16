package modelTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.ui.GUIManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreetTest {
    private EstateDAO estateDao;
    private Street street;
    private Person person;
    private PersonDAO personDao;
    private GUIManager guiManagerMock;
    @BeforeEach
    public void setUp() {
        person = new Person("testUser", "password");
        ArrayList<Estate> estates = new ArrayList<>();
        person.newEstates_add(estates);

        //estateDao = EstateDAO.getEstateDAO();
        street = new Street("Test Street", 1, 100, 10, 50, 60, 20, 30, 40, 50, 60, 70, 80, 90);
        personDao = PersonDAO.getPersonDAO();
        personDao.addPerson(person);
        personDao.setUserThatSignIn("testUser");



    }

    @Test
    public void testRentWithNoHousesOrHotels() {
        assertEquals(10, street.rent());
    }

    @Test
    public void testRentWithOneHouse() {
        street.setHouseCount(1);
        assertEquals(30, street.rent());
    }

    @Test
    public void testRentWithFourHouses() {
        street.setHouseCount(4);
        assertEquals(60, street.rent());
    }

    @Test
    public void testRentWithHotel() {
        street.setHouseCount(0);
        street.setHotelExist(true);
        assertEquals(70, street.rent());
    }
    /*
    @Test
    public void testBuyHouseWithEnoughMoney() {
        // Assuming the person has enough money
        assertTrue(street.buyHouse());
        assertEquals(1, street.getHouseCount());
    }*/
}
