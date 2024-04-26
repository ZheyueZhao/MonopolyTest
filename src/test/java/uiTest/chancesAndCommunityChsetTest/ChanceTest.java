package uiTest.chancesAndCommunityChsetTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.Generated;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.PieceWorker;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    @BeforeEach
    public void setUp(){
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
                        mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                        mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                        mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                        mockedClient.when(Client::getClient).thenReturn(mockelient);
                        spyObj.chance();
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
                        mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                        mockedClient.when(Client::getClient).thenReturn(mockelient);
                        spyObj.chance();
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
