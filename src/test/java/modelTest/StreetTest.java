package modelTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreetTest {
    private EstateDAO estateDao;
    private Street street;
    private Person person;
    private PersonDAO personDao;
    private GUIManager guiManagerMock;
    private Estate str1;
    private Estate str2;
    private Estate str3;
    private Estate str4;
    private Estate str5;
    private Estate str6;
    private Estate str7;
    private Estate str8;
    private Estate str9;
    private Estate str10;
    private Estate str11;
    private Estate str12;
    private Estate str13;
    private Estate str14;
    private Estate str15;
    private Estate str16;
    private Estate str17;
    private Estate str18;
    private Estate str19;
    private Estate str20;
    private Estate str21;
    private Estate str22;
    private Field EstateField;
    ArrayList<Estate> personEstates = new ArrayList<Estate>();
    private HashMap<Integer, Estate> estates;
    @BeforeEach
    public void setUp() throws NoSuchFieldException {
        str1 = new Street("MediterraneanAvenue", 1, 60, 2, 30, 33, 4, 10, 30, 90, 160, 250, 50, 50);
        str2 = new Street("BalticAvenue", 3, 60, 4, 30, 33, 8, 20, 60, 180, 320, 450, 50, 50);
        str3 = new Street("OrientalAvenue", 6, 100, 6, 50, 55, 12, 30, 90, 270, 400, 550, 50, 50);
        str4 = new Street("VermontAvenue", 8, 100, 6, 50, 55, 12, 30, 90, 270, 400, 550, 50, 50);
        str5 = new Street("ConnecticutAvenue", 9, 120, 8, 60, 66, 16, 40, 100, 300, 450, 600, 50, 50);
        str6 = new Street("St.CharlesPlace", 11, 140, 10, 70, 77, 20, 50, 150, 450, 625, 750, 100, 100);
        str7 = new Street("StatesAvenue", 13, 140, 10, 70, 77, 20, 50, 150, 450, 625, 750, 100, 100);
        str8 = new Street("VirginiaAvenue", 14, 160, 12, 80, 88, 24, 60, 180, 500, 700, 900, 100, 100);
        str9 = new Street("St.JamesPlace", 16, 180, 14, 90, 99, 28, 70, 200, 550, 750, 950, 100, 100);
        str10 = new Street("TennesseeAvenue", 18, 180, 14, 90, 99, 28, 70, 200, 550, 750, 950, 100, 100);
        str11 = new Street("NewyorkAvenue", 19, 200, 16, 100, 110, 32, 80, 220, 600, 800, 1000, 100, 100);
        str12 = new Street("KentuckyAvenue", 21, 220, 18, 110, 121, 36, 90, 250, 700, 875, 1050, 150, 150);
        str13 = new Street("IndiniaAvenue", 23, 220, 18, 110, 121, 36, 90, 250, 700, 875, 1050, 150, 150);
        str14 = new Street("IllinoisAvenue", 24, 240, 20, 120, 132, 40, 100, 300, 750, 925, 1100, 150, 150);
        str15 = new Street("AtlanticAvenue", 26, 260, 22, 130, 143, 44, 110, 330, 800, 975, 1150, 150, 150);
        str16 = new Street("VentnorAvenue", 27, 260, 22, 130, 143, 44, 110, 330, 800, 975, 1150, 150, 150);
        str17 = new Street("MarvinAvenue", 29, 280, 24, 140, 154, 48, 120, 360, 850, 1025, 1200, 150, 150);
        str18 = new Street("PacificAvenue", 31, 300, 26, 150, 165, 52, 130, 390, 900, 1100, 1275, 200, 200);
        str19 = new Street("NorthCaliforniaAvenue", 32, 300, 26, 150, 165, 52, 130, 390, 900, 1100, 1275, 200,
                200);
        str20 = new Street("PennsylvaniaAvenue", 34, 320, 28, 160, 176, 56, 150, 450, 1000, 1200, 1400, 200,
                200);
        str21 = new Street("5thAvenue", 37, 320, 28, 160, 176, 56, 150, 450, 1000, 1200, 1400, 200,
                200);
        str22 = new Street("PennsylvaniaAvenue", 39, 320, 28, 160, 176, 56, 150, 450, 1000, 1200, 1400, 200,
                200);
        estates = new HashMap<>();
        estates.put(1, str1);
        estates.put(3, str2);
        estates.put(6, str3);
        estates.put(8, str4);
        estates.put(9, str5);
        estates.put(11, str6);
        estates.put(13, str7);
        estates.put(14, str8);
        estates.put(16, str9);
        estates.put(18, str10);
        estates.put(19, str11);
        estates.put(21, str12);
        estates.put(23, str13);
        estates.put(24, str14);
        estates.put(26, str15);
        estates.put(27, str16);
        estates.put(29, str17);
        estates.put(31, str18);
        estates.put(32, str19);
        estates.put(34, str20);
        estates.put(37, str21);
        estates.put(39, str22);
        person = new Person("streettTstUser", "password");
        ArrayList<Estate> estates = new ArrayList<>();
        person.newEstates_add(estates);
        person.getEstates();
        street = new Street("Test Street", 1, 100, 10, 50, 60, 20, 30, 40, 50, 60, 70, 80, 90);
        personDao = PersonDAO.getPersonDAO();
        estateDao = EstateDAO.getEstateDAO();
        personDao.addPerson(person);
        personDao.setUserThatSignIn("streettTstUser");
        EstateField = Person.class.getDeclaredField("estates");
        EstateField.setAccessible(true);
    }

    @Order(1)
    @Test
    public void testColorSetRent() {
        street.setColorSetRent(100);
        assertEquals(100, street.getColorSetRent());
    }

    @Order(2)
    @Test
    public void testHs1Rent() {
        street.setHs1Rent(50);
        assertEquals(50, street.getHs1Rent());
    }

    @Order(3)
    @Test
    public void testHs2Rent() {
        street.setHs2Rent(50);
        assertEquals(50, street.getHs2Rent());
    }

    @Order(4)
    @Test
    public void testHs3Rent() {
        street.setHs3Rent(50);
        assertEquals(50, street.getHs3Rent());
    }

    @Order(5)
    @Test
    public void testHs4Rent() {
        street.setHs4Rent(50);
        assertEquals(50, street.getHs4Rent());
    }

    @Order(6)
    @Test
    public void testHt1Rent() {
        street.setHt1Rent(50);
        assertEquals(50, street.getHt1Rent());
    }

    @Order(7)
    @Test
    public void testColor() {
        street.setColorSet(true);
        assertEquals(true, street.isColorSet());
    }

    @Order(8)
    @Test
    public void hotelRent() {
        street.setHotelsCost(50);
        assertEquals(50, street.getHotelsCost());
    }

    @Order(9)
    @Test
    public void testRentWithNoHousesOrHotels() {
        assertEquals(10, street.rent());
    }

    @Test
    @Order(10)
    public void testRentWithOneHouse() {
        street.setHouseCount(1);
        assertEquals(30, street.rent());
    }

    @Test
    @Order(11)
    public void testRentWithFourHouses() {
        street.setHouseCount(4);
        assertEquals(60, street.rent());
    }

    @Test
    @Order(12)
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
    }
*/
    @Test
    @Order(13)
    public void testHaveEstate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Estate> personEstates = new ArrayList<Estate>();
        personEstates.add(str1);
        personDao.getThePerson().setEstates(personEstates);

        Class<?> streetTestClass = street.getClass();
        Method haveEstateMethod = streetTestClass.getDeclaredMethod("haveEstate",int.class);
        haveEstateMethod.setAccessible(true);

        assert (Boolean) haveEstateMethod.invoke(street, 1) == true;

    }

    @Test
    @Order(14)
    public void testHaveNoEstate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> streetTestClass = street.getClass();
        Method haveEstateMethod = streetTestClass.getDeclaredMethod("haveEstate",int.class);
        haveEstateMethod.setAccessible(true);
        assert (Boolean) haveEstateMethod.invoke(street, 99) == false;

    }

    @Test
    @Order(15)
    public void testBuyHouse() throws IllegalAccessException {
            try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
                try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                    estateDao.changeEstateDAO(estates);
                    PersonDAO mockPersonDao = mock(PersonDAO.class);
                    personEstates.add(str1);
                    personEstates.add(str2);
                    EstateField.set(person, personEstates);
                    estateDao.getOneEstate(1).setMortgage(true);
                    mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                    when(mockPersonDao.getThePerson()).thenReturn(person);
                    assert street.buyHouse() == false;
                    estateDao.getOneEstate(1).setMortgage(false);
                }
        }
    }


    @Test
    @Order(16)
    public void testBuyHouseNoHouse() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str1);
                personEstates.add(str2);
                EstateField.set(person, personEstates);
                Street street3 = (Street) estateDao.getEstates().get(3);
                street3.setHouseCount(-1);
                estateDao.changeEstate(street3);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;

            }
        }
    }

    @Test
    @Order(17)
    public void testBuyHouseNoMoney() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setHousesCost(9999999);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str1);
                personEstates.add(str2);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }

    }

    @Test
    @Order(18)
    public void testBuyHouseCount4() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str1);
                personEstates.add(str2);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }

    }

    @Test
    @Order(11)
    public void testBuyHouseCount3() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setHousesCost(0); street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str1); personEstates.add(str2);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }

    }

    @Test
    @Order(12)
    public void testBuyHousePanel6() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(6);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str3);personEstates.add(str4);personEstates.add(str5);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(6).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(6).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel6() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(6);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str3);personEstates.add(str4);personEstates.add(str5);
                EstateField.set(person, personEstates);
                Street street8 = (Street) estateDao.getEstates().get(8);
                street8.setHouseCount(-1);
                estateDao.changeEstate(street8);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street8.setHouseCount(0);
                estateDao.changeEstate(street8);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel8() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(6);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str3);personEstates.add(str4);personEstates.add(str5);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }

    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel8() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(6);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str3);personEstates.add(str4);personEstates.add(str5);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }

    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel6() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(6);
                street.setHousesCost(0); street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str3);personEstates.add(str4);personEstates.add(str5);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel11() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(11);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str6);personEstates.add(str7);personEstates.add(str8);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(11).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(11).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel11() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(11);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str6);personEstates.add(str7);personEstates.add(str8);
                EstateField.set(person, personEstates);
                Street street13 = (Street) estateDao.getEstates().get(13);
                street13.setHouseCount(-1);
                estateDao.changeEstate(street13);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street13.setHouseCount(0);
                estateDao.changeEstate(street13);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel11() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(11);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str6);personEstates.add(str7);personEstates.add(str8);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }

    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel11() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(11);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str6);personEstates.add(str7);personEstates.add(str8);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel11() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(11);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str6);personEstates.add(str7);personEstates.add(str8);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel16() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(16);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str9);personEstates.add(str10);personEstates.add(str11);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(16).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(16).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel16() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(16);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str9);personEstates.add(str10);personEstates.add(str11);
                EstateField.set(person, personEstates);
                Street street18 = (Street) estateDao.getEstates().get(18);
                street18.setHouseCount(-1);
                estateDao.changeEstate(street18);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street18.setHouseCount(0);
                estateDao.changeEstate(street18);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel16() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(16);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str9);personEstates.add(str10);personEstates.add(str11);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }
    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel16() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(16);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str9);personEstates.add(str10);personEstates.add(str11);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel16() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(16);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str9);personEstates.add(str10);personEstates.add(str11);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel21() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(21);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str12);personEstates.add(str13);personEstates.add(str14);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(21).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(21).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel21() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(21);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str12);personEstates.add(str13);personEstates.add(str14);
                EstateField.set(person, personEstates);
                Street street23 = (Street) estateDao.getEstates().get(23);
                street23.setHouseCount(-1);
                estateDao.changeEstate(street23);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street23.setHouseCount(0);
                estateDao.changeEstate(street23);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel21() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(21);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str12);personEstates.add(str13);personEstates.add(str14);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }
    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel21() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(21);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str12);personEstates.add(str13);personEstates.add(str14);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel21() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(21);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str12);personEstates.add(str13);personEstates.add(str14);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel26() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(26);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str15);personEstates.add(str16);personEstates.add(str17);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(26).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(26).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel26() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(27);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str15);personEstates.add(str16);personEstates.add(str17);
                EstateField.set(person, personEstates);
                Street street27 = (Street) estateDao.getEstates().get(27);
                street27.setHouseCount(1);
                estateDao.changeEstate(street27);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street27.setHouseCount(0);
                estateDao.changeEstate(street27);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel26() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(26);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str15);personEstates.add(str16);personEstates.add(str17);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }
    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel26() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(26);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str15);personEstates.add(str16);personEstates.add(str17);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel26() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(26);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str15);personEstates.add(str16);personEstates.add(str17);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel31() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(31);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str18);personEstates.add(str19);personEstates.add(str20);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(31).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(31).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel31() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(32);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str18);personEstates.add(str19);personEstates.add(str20);
                EstateField.set(person, personEstates);
                Street street32 = (Street) estateDao.getEstates().get(32);
                street32.setHouseCount(1);
                estateDao.changeEstate(street32);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street32.setHouseCount(0);
                estateDao.changeEstate(street32);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel31() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(31);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str18);personEstates.add(str19);personEstates.add(str20);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }
    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel31() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(31);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str18);personEstates.add(str19);personEstates.add(str20);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel31() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(31);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str18);personEstates.add(str19);personEstates.add(str20);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }

    @Test
    @Order(12)
    public void testBuyHousePanel37() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(37);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str21);personEstates.add(str22);
                EstateField.set(person, personEstates);
                estateDao.getOneEstate(37).setMortgage(true);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                estateDao.getOneEstate(37).setMortgage(false);
            }
        }
    }

    @Test
    @Order(13)
    public void testBuyHouseNoHousePanel37() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(39);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str21);personEstates.add(str22);
                EstateField.set(person, personEstates);
                Street street39 = (Street) estateDao.getEstates().get(39);
                street39.setHouseCount(1);
                estateDao.changeEstate(street39);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street39.setHouseCount(0);
                estateDao.changeEstate(street39);
            }
        }
    }

    @Test
    @Order(14)
    public void testBuyHouseNoMoneyPanel37() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(37);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str21);personEstates.add(str22);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == false;
                street.setHousesCost(90);
            }
        }
    }

    @Test
    @Order(15)
    public void testBuyHouseCount4Panel37() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(37);
                street.setHousesCost(0);
                street.setHouseCount(4);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str21);personEstates.add(str22);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.isHotelExist() == true;
            }
        }
    }

    @Test
    @Order(16)
    public void testBuyHouseCount3Panel37() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(37);
                street.setHousesCost(0);
                street.setHouseCount(3);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                personEstates.add(str21);personEstates.add(str22);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
                assert street.getHouseCount() == 4;
            }
        }
    }



    @Test
    @Order(16)
    public void testBuyHouseCount3PanelOOB() throws IllegalAccessException {
        try(MockedStatic mockedPersonDao = mockStatic(PersonDAO.class)) {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                estateDao.changeEstateDAO(estates);
                street.setPanelNo(100);
                PersonDAO mockPersonDao = mock(PersonDAO.class);
                EstateField.set(person, personEstates);
                mockedPersonDao.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDao);
                when(mockPersonDao.getThePerson()).thenReturn(person);
                assert street.buyHouse() == true;
            }
        }
    }

    @Test
    public void testHouseExistInAllStreets() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        estateDao.changeEstateDAO(estates);
        HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
        Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
        s1.setHotelExist(true);
        EstateDAO.getEstateDAO().changeEstate(s1);

        Class<?> streetTestClass = street.getClass();
        Method haveEstateMethod = streetTestClass.getDeclaredMethod("houseExistInAllStreets");
        haveEstateMethod.setAccessible(true);

        assert (Boolean) haveEstateMethod.invoke(street) == true;
    }

    static Stream<Integer> streetIdsProvider() {
        return Stream.of(1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29,31, 32, 34, 37, 39);
    }


    @ParameterizedTest
    @MethodSource("streetIdsProvider")
    public void testHouseExistInAllStreetsS1(int streetId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assert testHouseExistInAllStreetsForStreet(streetId) == true;
    }


    private boolean testHouseExistInAllStreetsForStreet(int streetId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        estateDao.changeEstateDAO(estates);
        HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
        Street street = (Street) test.get(streetId);
        street.setHotelExist(true);
        EstateDAO.getEstateDAO().changeEstate(street);

        Class<?> streetTestClass = street.getClass();
        Method haveEstateMethod = streetTestClass.getDeclaredMethod("houseExistInAllStreets");
        haveEstateMethod.setAccessible(true);

        return  (Boolean) haveEstateMethod.invoke(street);
    }

    @ParameterizedTest
    @CsvSource({"1,3","3,1","6,8","6,9","8,6","8,9","9,6","9,8",
            "11,13","11,14","13,11","13,14","14,11","14,13","16,18","16,19","18,16","18,19","19,16","19,18"
    ,"21,23","23,21","23,24","24,21","24,23","26,29","27,26","27,29","29,26","29,27",
    "31,32","31,34","32,31","32,34","34,31","34,32","37,39","39,37"})
    public void testHouseExistInAllStreetsS1Hotel(int streetId, int hotelId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assert testHouseExistInAllStreetsForStreetHotelExists(streetId, hotelId) == true;
    }

    private boolean testHouseExistInAllStreetsForStreetHotelExists(int streetId, int hotelId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        estateDao.changeEstateDAO(estates);
        HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
        Street street = (Street) test.get(streetId);
        Street street1 = (Street) test.get(hotelId);
        street1.setHotelExist(true);
        street.setHouseCount(street1.getHouseCount()+1);
        street.setHotelExist(true);
        EstateDAO.getEstateDAO().changeEstate(street);
        EstateDAO.getEstateDAO().changeEstate(street1);

        Class<?> streetTestClass = street.getClass();
        Method haveEstateMethod = streetTestClass.getDeclaredMethod("houseExistInAllStreets");
        haveEstateMethod.setAccessible(true);

        return  (Boolean) haveEstateMethod.invoke(street);
    }

    @ParameterizedTest
    @MethodSource("streetIdsProvider")
    public void testSellHouseAllStreetsHotelExist(int streetId){
        estateDao.changeEstateDAO(estates);
        street.setPanelNo(streetId);
        street.setHotelExist(true);
        assert street.sellHouse() == true;
        assert street.isHotelExist() == false;
        assert street.getHouseCount() == 4;
        street.setHouseCount(0);
    }

    @ParameterizedTest
    @MethodSource("streetIdsProvider")
    @Order(1)
    public void testSellHouseAllStreetsHotelDoesNotExist(int streetId){
        street.setPanelNo(streetId);
        estateDao.changeEstateDAO(estates);
        HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
        Street street = (Street) test.get(streetId);
        street.setHouseCount(100);
        street.setHotelExist(false);
        EstateDAO.getEstateDAO().changeEstate(street);

        assert street.sellHouse() == true;
        assert street.getHouseCount() == 99;
        street.setHouseCount(0);
        EstateDAO.getEstateDAO().changeEstate(street);
    }

    @Test
    public void testSellHouseZero(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            street.setHotelExist(false);
            street.setHouseCount(0);
            assert street.sellHouse() == false;
            street.setHouseCount(0);
            EstateDAO.getEstateDAO().changeEstate(street);
        }
    }

    @Test
    public void testSellHouseOutOfBound(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            street.setPanelNo(99);
            street.setHouseCount(1);
            assert street.sellHouse() == false;
            street.setHouseCount(0);
            EstateDAO.getEstateDAO().changeEstate(street);
        }
    }

    @ParameterizedTest
    @MethodSource("streetIdsProvider")
    public void testMortgageAllStreets(int streetId){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            street.setPanelNo(streetId);
            HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
            Street street = (Street) test.get(streetId);
            street.setHouseCount(1);
            assert street.mortgage() == false;
            street.setHouseCount(0);
            EstateDAO.getEstateDAO().changeEstate(street);
        }
    }

    @Test
    public void testMortgageAlreadyMortgage(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            street.setMortgage(true);
            assert street.mortgage() == false;
            street.setMortgage(false);
        }
    }

    @Test
    public void testMortgageSuccess(){
        estateDao.changeEstateDAO(estates);
        street.setPanelNo(99);
        assert street.mortgage() == true;
    }

    @Test
    public void testUnMortgageNotMortgaged(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            street.setPanelNo(3);
            assert street.unMortgage() == false;
        }
    }

    @Test
    public void testUnMortgageNotEnoughMoney(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
            Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
            s1.setMortgage(true);

            street.setPanelNo(1);
            assert street.unMortgage() == false;
        }
    }

    @Test
    public void testUnMortgageSuccess(){
        try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
            estateDao.changeEstateDAO(estates);
            HashMap<Integer, Estate> test = EstateDAO.getEstateDAO().getEstates();
            Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
            s1.setMortgage(true);
            s1.setUnMortgage(-5);

            street.setPanelNo(1);
            assert street.unMortgage() == true;
        }
    }



}

