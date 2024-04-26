package uiTest.chancesAndCommunityChsetTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class CommunityChestTest {
    GamePanel mockGamePanel;
    EstateDAO mockEstateDAO;
    PlayerDAO mockPlayerDAO;
    DicePanel mockedDicePanel;
    PlayerPanel mockPlayerPanel;
    EstatesPanel mockEstatePanel;
    Client mockelient;
    Estate mockEstate;
    PersonDAO mockPersonDAO;
    Person mockPerson;
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
        mockPerson = mock(Person.class);
    }

    static Stream<Integer> diceRolls() {
        return Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);
    }

    static Stream<Integer> diceRolls69() {
        return Stream.of(6,9);
    }

    @ParameterizedTest
    @MethodSource("diceRolls")
    public void communityChestOne(int dice){
        CommunityChest spyObj = spy(CommunityChest.class);
        when(spyObj.makeRandom()).thenReturn(dice);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockePersonDAO = mockStatic(PersonDAO.class)) {
                    try (MockedStatic mockGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockePersonDAO.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                            mockePersonDAO.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockePersonDAO.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockePersonDAO.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                            when(mockPerson.getLocation()).thenReturn(1);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.communityChest();
                        }
                    }
                }
            }
        });
    }

    @Test
    public void communityChest13(){
        CommunityChest spyObj = spy(CommunityChest.class);
        when(spyObj.makeRandom()).thenReturn(14);
        ArrayList<Estate> PersonEstates = new ArrayList<>();
        Street estate = new Street("Test Estate", 1, 200, 20, 100, 120);
        estate.setHotelExist(true);
        PersonEstates.add(estate);

        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockePersonDAO = mockStatic(PersonDAO.class)) {
                    try (MockedStatic mockGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockePersonDAO.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                            mockePersonDAO.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                            when(mockPerson.getLocation()).thenReturn(1);
                            when(mockPerson.getEstates()).thenReturn(PersonEstates);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.communityChest();
                        }
                    }
                }
            }
        });
    }

    @Test
    public void communityChestFive(){
        CommunityChest spyObj = spy(CommunityChest.class);
        when(spyObj.makeRandom()).thenReturn(5);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockePersonDAO = mockStatic(PersonDAO.class)) {
                    try (MockedStatic mockGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            mockePersonDAO.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                            mockePersonDAO.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                            mockePersonDAO.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                            mockePersonDAO.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                            when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                            when(mockPerson.getLocation()).thenReturn(1);
                            when(mockPerson.HaveJailCard()).thenReturn(true);
                            mockedClient.when(Client::getClient).thenReturn(mockelient);
                            spyObj.communityChest();
                        }
                    }
                }
            }
        });
    }

    @ParameterizedTest
    @MethodSource("diceRolls69")
    public void communityChestSix(int dice){
        HashMap<String, Person> playersMap = new HashMap<>();
        Person newPerson = new Person("test", "password");
        playersMap.put("test",newPerson);
        CommunityChest spyObj = spy(CommunityChest.class);
        when(spyObj.makeRandom()).thenReturn(dice);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockePersonDAO = mockStatic(PersonDAO.class)) {
                    try (MockedStatic mockGUIManager = mockStatic(GUIManager.class)) {
                        try (MockedStatic mockedClient = mockStatic(Client.class)) {
                            try (MockedStatic mockPlayerDao = mockStatic(PlayerDAO.class)) {
                                mockPlayerDao.when(PlayerDAO::getPlayerDAO).thenReturn(mockPlayerDAO);
                                mockePersonDAO.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                                mockePersonDAO.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                                mockePersonDAO.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                                mockePersonDAO.when(GUIManager::getDicePanel).thenReturn(mockedDicePanel);
                                when(mockPlayerDAO.getPlayers()).thenReturn(playersMap);
                                when(mockPersonDAO.getThePerson()).thenReturn(mockPerson);
                                when(mockPerson.getLocation()).thenReturn(1);
                                mockedClient.when(Client::getClient).thenReturn(mockelient);
                                spyObj.communityChest();
                            }
                        }
                    }
                }
            }
        });
    }
}
