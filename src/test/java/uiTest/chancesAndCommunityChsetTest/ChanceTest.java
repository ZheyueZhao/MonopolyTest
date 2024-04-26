package uiTest.chancesAndCommunityChsetTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.*;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class ConcreteEstateChances extends Estate {
    public ConcreteEstateChances(String name, int panelNo, int price, int rent, int mortgage, int unMortgage) {
        super(name, panelNo, price, rent, mortgage, unMortgage);
    }
    // Implement constructors and methods of ConcreteEstate
}


public class ChanceTest {
    PlayerDAO playerDAO;
    PersonDAO personDAO;
    Person person;
    GamePanel mockGamePanel;
    EstateDAO mockEstateDAO;
    PlayerDAO mockPlayerDAO;
    DicePanel mockedDicePanel;
    PlayerPanel mockPlayerPanel;
    EstatesPanel mockEstatePanel;
    Client mockelient;
    Estate mockEstate;
    PersonDAO mockPersonDAO;;
    private HashMap<Integer, Estate> estates;
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
    private Estate utl1;
    private Estate utl2;
    private Estate rr1;
    private Estate rr2;
    private Estate rr3;
    private Estate rr4;

    @BeforeEach
    public void setUp(){
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
        utl1 = new Utility("ElectricCompany", 12, 150, 4, 75, 83, 10);
        utl2 = new Utility("WaterWork", 28, 150, 4, 75, 83, 10);
        rr1 = new RailRoad("ReadingRailroad", 5, 200, 25, 100, 110, 50, 100, 200);
        rr2 = new RailRoad("PennsylvaniaRailroad", 15, 200, 25, 100, 110, 50, 100, 200);
        rr3 = new RailRoad("B&ORailroad", 25, 200, 25, 100, 110, 50, 100, 200);
        rr4 = new RailRoad("ShortLine", 35, 200, 25, 100, 110, 50, 100, 200);

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
        estates.put(12, utl1);
        estates.put(28, utl2);
        estates.put(5, rr1);
        estates.put(15, rr2);
        estates.put(25, rr3);
        estates.put(35, rr4);

        mockEstatePanel = mock(EstatesPanel.class);
        mockGamePanel = Mockito.mock(GamePanel.class);
        mockEstateDAO = Mockito.mock(EstateDAO.class);
        mockPlayerDAO = Mockito.mock(PlayerDAO.class);
        mockedDicePanel = Mockito.mock(DicePanel.class);
        mockPlayerPanel = Mockito.mock(PlayerPanel.class);
        mockPersonDAO = mock(PersonDAO.class);
        mockelient = mock(Client.class);
        mockEstate = mock(Estate.class);
        playerDAO = PlayerDAO.getPlayerDAO();
        person = new Person("testUser","password");
        personDAO = PersonDAO.getPersonDAO();
        Estate estate = new ConcreteEstateChances("Test Estate", 1, 200, 20, 100, 120);
        ArrayList<Estate> testEstates = new ArrayList<>();
        testEstates.add(estate);
        person.getEstates();
        playerDAO.addPlayer(person);
        PersonDAO personDAO = PersonDAO.getPersonDAO();
        personDAO.getPersons();
        PlayerPanel mockPlayerPanel = mock(PlayerPanel.class);

    }
    @Test
    public void setYesAction() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Chance chanceTest = new Chance();
        Chance spyObj = spy(chanceTest);

        Class<?> pieceWorkerTestClass = chanceTest.getClass();
        Method chanceMethod = pieceWorkerTestClass.getDeclaredMethod("makeRandom");
        chanceMethod.setAccessible(true);
        chanceMethod.invoke(chanceTest);
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < 30000; i++) {

            int randomNumber = (int) chanceMethod.invoke(chanceTest);
            frequencyMap.put(randomNumber, frequencyMap.getOrDefault(randomNumber, 0) + 1);
        }

        for (int number = 1; number < 7; number++) {
            int frequency = frequencyMap.getOrDefault(number, 0);
            for(int num=1; num<7;num++){
                int alterFrequency = frequencyMap.getOrDefault(number, 0);
                assert frequency <= alterFrequency*1.5 && frequency >= alterFrequency*0.5;
            }

        }

    }

    //This test is functional and achieves coverage but it is a bad test. so it is currently disabled until i figure oout an improvement

    public void mockChanceOneHelper(){
        doNothing().when(mockelient).sendObject(any());
        doNothing().when(mockPlayerPanel).setPriceLabel(anyString());
        when(mockGamePanel.distance(anyInt(), anyInt())).thenReturn(100);
        when(mockEstateDAO.getOneEstate(anyInt())).thenReturn(mockEstate);
    }

    static Stream<Integer> diceRolls() {
        return Stream.of(4,5,14);
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOne(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(7);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstate.isOwned()).thenReturn(true);
                            when(mockEstate.getOwner()).thenReturn("testUserNot");
                            mockChanceOneHelper();
                            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOneNotOwned(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(7);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        person.setMoney(1000);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(mockEstatePanel);
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstate.isOwned()).thenReturn(false);
                            when(mockEstate.getOwner()).thenReturn("testUserNot");
                            mockChanceOneHelper();
                            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOneNotOwned22(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        person.setMoney(1000);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(mockEstatePanel);
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstate.isOwned()).thenReturn(false);
                            when(mockEstate.getOwner()).thenReturn("testUserNot");
                            mockChanceOneHelper();
                            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOne22(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstate.isOwned()).thenReturn(true);
                            when(mockEstate.getOwner()).thenReturn("testUserNot");
                            mockChanceOneHelper();
                            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOneEnoughMoney(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(7);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            try (MockedStatic mockedPlayerDAO = mockStatic(PlayerDAO.class)) {
                                mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                                mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                                mockedEstateDAO.when(PlayerDAO::getPlayerDAO).thenReturn(mockPlayerDAO);
                                when(mockPlayerDAO.getOnePlayer(anyString())).thenReturn(person);
                                when(mockEstate.isOwned()).thenReturn(true);
                                when(mockEstate.getOwner()).thenReturn("testUserNot");
                                mockChanceOneHelper();
                                mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                                mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                                mockedClient.when(Client::getClient).thenReturn(mockelient);

                                spyObj.chance();

                            }
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void chanceOneEnoughMoney22(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            try (MockedStatic mockedPlayerDAO = mockStatic(PlayerDAO.class)) {
                                mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                                mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                                mockedEstateDAO.when(PlayerDAO::getPlayerDAO).thenReturn(mockPlayerDAO);
                                when(mockPlayerDAO.getOnePlayer(anyString())).thenReturn(person);
                                when(mockEstate.isOwned()).thenReturn(true);
                                when(mockEstate.getOwner()).thenReturn("testUserNot");
                                mockChanceOneHelper();
                                mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                                mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                                mockedClient.when(Client::getClient).thenReturn(mockelient);

                                spyObj.chance();

                            }
                        }
                    }
                }
            }
        });
    }
    static Stream<Integer> diceRolls12() {
        return Stream.of(1,2);
    }

    @ParameterizedTest
    @MethodSource("diceRolls12")
    public void chanceRollOne(int rolls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(rolls);
        personDAO.removePerson(person);
        person.setLocation(27);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstateDAO.getEstates()).thenReturn(estates);
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }

            }
        });
    }

    @Test
    public void chanceRollThree() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(3);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedStreetAction = mockStatic(StreetActions.class)) {
                            try(MockedConstruction<StreetActions> mockedChance = mockConstruction(StreetActions.class)){

                                mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                                mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                                mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                                mockedClient.when(Client::getClient).thenReturn(mockelient);
                                spyObj.chance();
                                StreetActions streetActionsMock = mockedChance.constructed().get(0);
                            }
                        }
                    }

                }}
        });
    }

    static Stream<Integer> otherDiceRolls671115() {
        return Stream.of(6,7,11,15);
    }

    @ParameterizedTest
    @MethodSource("otherDiceRolls671115")
    public void chanceSix(int dice) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(dice);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    mockedClient.when(Client::getClient).thenReturn(mockelient);
                    spyObj.chance();
                }

            }
        });
    }

    static Stream<Integer> otherDiceRolls8() {
        return Stream.of(8,9);
    }

    @ParameterizedTest
    @MethodSource("otherDiceRolls8")
    public void chanceEight(int dice) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(dice);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                            mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(mockEstateDAO);
                            when(mockEstateDAO.getEstates()).thenReturn(estates);
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.chance();
                        }
                    }
                }

            }
        });
    }

    @Test
    public void chanceEightJailCard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(9);
        personDAO.removePerson(person);
        person.setHaveJailCard(true);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                        mockedClient.when(Client::getClient).thenReturn(mockelient);
                        spyObj.chance();
                    }
                }

            }
        });
    }

    @Test
    public void chanceTen() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(10);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        Person mockPerson = mock(Person.class);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedPersonDAO = mockStatic(PersonDAO.class)) {
                        mockedClient.when(Client::getClient).thenReturn(mockelient);
                        mockedClient.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                        when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                        ArrayList<Estate> newEstates = new ArrayList<>();
                        Street street = new Street("NewStreet", 777, 100, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110);
                        street.setHotelExist(true);
                        newEstates.add(street);
                        when(mockPerson.getEstates()).thenReturn(newEstates);
                        spyObj.chance();
                    }
                }

            }
        });
    }

    @Test
    public void chanceTwelve() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Chance spyObj = spy(Chance.class);
        when(spyObj.makeRandom()).thenReturn(12);
        personDAO.removePerson(person);
        person.setLocation(22);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        Person mockPerson = mock(Person.class);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedClient = mockStatic(Client.class)) {
                    try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedPersonDAO = mockStatic(PersonDAO.class)) {
                            mockedClient.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                            when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            doNothing().when(mockGamePanel).movePieceOnePlace(anyInt(),anyInt());
                            doNothing().when(mockPerson).setMoney(anyInt());
                            spyObj.chance();
                    }}
                }

            }
        });
    }




}
