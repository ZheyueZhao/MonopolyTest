package modelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jdk.jshell.execution.Util;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.*;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;


import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreetActionTestClass {
    private static HashMap<Integer, Estate> testEstates;
    private Estate estate;
    private PersonDAO personDAO;
    private Person person;
    private Person person2;
    private EstateDAO estateDAO;
    private PlayerDAO playerDAO;
    private Client clientMock;
    private Server server;
    private StreetActions streetActions = new StreetActions();
    private Street street;


    @BeforeEach
    public void setUp(){
        // guiManagerMock = mock(GUIManager.class);
        person = new Person("testUser", "password");
        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        estateDAO = EstateDAO.getEstateDAO();


    }

    @Test
    @Order(1)
    public void testPurchaseNoMoney() {
        Client sampleMock = mock(Client.class);

        person.setMoney(2);
        person.setLocation(1);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert !testEstates.get(1).isOwned();
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    static Stream<Integer> streetIdsProvider() {
        return Stream.of(1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29,31, 32, 34, 37, 39);
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("streetIdsProvider")
    public void testPurchaseMoney(int streetId) {
        Client sampleMock = mock(Client.class);
        person.setMoney(99999);
        person.setLocation(streetId);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();
        BuyStreetDialog dialogMock = mock(BuyStreetDialog.class);
        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        mockedClient.when(Client::getClient).thenReturn(sampleMock);
                        mockedGUIManager.when(GUIManager::getBuyStreetDialog).thenReturn(dialogMock);
                        mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(new PlayerPanel());
                        mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(new DicePanel());
                        mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(new EstatesPanel());
                        doNothing().when(dialogMock).setVisible(true);
                        doNothing().when(sampleMock).sendObject(any());
                        Field buyField = StreetActions.class.getDeclaredField("buy");
                        buyField.setAccessible(true);
                        boolean buy = (boolean) buyField.get(streetActions);
                        buy = true;
                        buyField.set(streetActions, buy);
                        streetActions.action();
                        assert testEstates.get(1).isOwned();
                        mockedClient.verify(Client::getClient);
                        verify(sampleMock).sendObject(any());
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }}

    }

    @Test
    @Order(3)
    public void testPurchaseMoneyAlreadyOwned() {
        Client sampleMock = mock(Client.class);
        person2 = new Person("owner","password");
        personDAO.addPerson(person2);
        person.setMoney(99999);
        person2.setMoney(0);
        person.setLocation(1);
        playerDAO.addPlayer(person);
        playerDAO.addPlayer(person2);
        personDAO.changePerson(person);

        estate = estateDAO.getOneEstate(1);
        estate.setOwned(true);
        estate.setOwner("owner");
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert person2.getMoney() == 2;
                assert person.getMoney() == 99997;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(4)
    public void testPurchaseNoMoneyRailroad() {
        Client sampleMock = mock(Client.class);

        person.setMoney(2);
        person.setLocation(5);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();



        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert !testEstates.get(5).isOwned();
                assert !person.getEstates().contains(5);
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(5)
    public void testPurchaseMoneyRailroad() {
        Client sampleMock = mock(Client.class);
        person.setMoney(99999);
        person.setLocation(5);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();
        BuyStreetDialog dialogMock = mock(BuyStreetDialog.class);

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    mockedClient.when(Client::getClient).thenReturn(sampleMock);
                    mockedGUIManager.when(GUIManager::getBuyStreetDialog).thenReturn(dialogMock);
                    mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(new PlayerPanel());
                    mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(new DicePanel());
                    mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(new EstatesPanel());
                    doNothing().when(dialogMock).setVisible(true);
                    mockedClient.when(Client::getClient).thenReturn(sampleMock);
                    doNothing().when(sampleMock).sendObject(any());

                    Field buyField = StreetActions.class.getDeclaredField("buy");
                    ((Field) buyField).setAccessible(true);
                    boolean buy = (boolean) buyField.get(streetActions);
                    buy = true;
                    buyField.set(streetActions, buy);

                    JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                    streetActions.action();
                    assert testEstates.get(5).isOwned();
                    assert testEstates.get(5).getOwner().equals(person.getUserName());
                    mockedClient.verify(Client::getClient);
                    verify(sampleMock).sendObject(any());
            } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }}
    }

    @Test
    @Order(6)
    public void testPurchaseMoneyAlreadyOwnedRailroad() {
        Client sampleMock = mock(Client.class);
        person2 = new Person("owner","password");
        personDAO.addPerson(person2);
        person.setMoney(99999);
        person2.setMoney(0);
        person.setLocation(5);
        playerDAO.addPlayer(person);
        playerDAO.addPlayer(person2);
        personDAO.changePerson(person);
        testEstates = estateDAO.getEstates();
        RailRoad r = mock(RailRoad.class);
        when(r.rent()).thenReturn(25);
        when(r.isOwned()).thenReturn(true);
        when(r.getOwner()).thenReturn("owner");
        testEstates.put(5,r);
        estateDAO.changeEstateDAO(testEstates);

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert person2.getMoney() == 25;
                assert person.getMoney() == 99974;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(7)
    public void testPurchaseNoMoneyUtility() {
        Client sampleMock = mock(Client.class);

        person.setMoney(2);
        person.setLocation(12);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();



        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert !testEstates.get(12).isOwned();
                assert !person.getEstates().contains(12);
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(8)
    public void testPurchaseMoneyUtility() {
        Client sampleMock = mock(Client.class);
        person.setMoney(99999);
        person.setLocation(12);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();
        BuyStreetDialog dialogMock = mock(BuyStreetDialog.class);
        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                    try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                        mockedClient.when(Client::getClient).thenReturn(sampleMock);

                        mockedGUIManager.when(GUIManager::getBuyStreetDialog).thenReturn(dialogMock);
                        mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(new PlayerPanel());
                        mockedGUIManager.when(GUIManager::getDicePanel).thenReturn(new DicePanel());
                        mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(new EstatesPanel());
                        mockedClient.when(Client::getClient).thenReturn(sampleMock);
                        doNothing().when(sampleMock).sendObject(any());
                        doNothing().when(dialogMock).setVisible(true);

                        Field buyField = StreetActions.class.getDeclaredField("buy");
                        buyField.setAccessible(true);
                        boolean buy = (boolean) buyField.get(streetActions);
                        buy = true;
                        buyField.set(streetActions, buy);

                        streetActions.action();
                        assert testEstates.get(12).isOwned();
                        assert testEstates.get(12).getOwner().equals(person.getUserName());
                        mockedClient.verify(Client::getClient);
                        verify(sampleMock).sendObject(any());
            } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }}
    }

    @Test
    @Order(9)
    public void testPurchaseMoneyAlreadyOwnedUtility() {
        Client sampleMock = mock(Client.class);
        person2 = new Person("owner","password");
        personDAO.addPerson(person2);
        person.setMoney(99999);
        person2.setMoney(0);
        person.setLocation(12);
        playerDAO.addPlayer(person);
        playerDAO.addPlayer(person2);
        personDAO.changePerson(person);
        testEstates = estateDAO.getEstates();
        Utility u = mock(Utility.class);
        when(u.rent()).thenReturn(25);
        when(u.isOwned()).thenReturn(true);
        when(u.getOwner()).thenReturn("owner");
        testEstates.put(12,u);
        estateDAO.changeEstateDAO(testEstates);

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert person2.getMoney() == 25;
                assert person.getMoney() == 99974;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(10)
    public void testPurchaseNoMoneyChance() {
        Client sampleMock = mock(Client.class);

        person.setMoney(2);
        person.setLocation(7);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedConstruction<Chance> mockedChance = mockConstruction(Chance.class)){
                //Chance chanceMock = new Chance();
                mockedClient.when(Client::getClient).thenReturn(sampleMock);

                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();
                Chance chanceMock = mockedChance.constructed().get(0);
                verify(chanceMock).chance();
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(11)
    public void testPurchaseNoMoneyCommunity() {
        Client sampleMock = mock(Client.class);

        person.setMoney(2);
        person.setLocation(17);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedConstruction<CommunityChest> mockedCommunityChest = mockConstruction(CommunityChest.class)){
                //Chance chanceMock = new Chance();
                mockedClient.when(Client::getClient).thenReturn(sampleMock);

                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();
                CommunityChest communityChestMock = mockedCommunityChest.constructed().get(0);
                verify(communityChestMock).communityChest();
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(12)
    public void testPurchaseLossMoney() {
        Client sampleMock = mock(Client.class);

        person.setMoney(1000);
        person.setLocation(4);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();



        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedConstruction<Data> mockedData = mockConstruction(Data.class)) {
                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();


                assert mockedData.constructed().size() == 1;
                assert person.getMoney() == 800;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(13)
    public void testPurchaseLossLessMoney() {
        Client sampleMock = mock(Client.class);

        person.setMoney(1000);
        person.setLocation(38);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();


        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedConstruction<Data> mockedData = mockConstruction(Data.class)) {
                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();


                assert mockedData.constructed().size() == 1;
                assert person.getMoney() == 900;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());
            }
        }
    }

    @Test
    @Order(13)
    public void testJailCard() {
        Client sampleMock = mock(Client.class);
        person.setHaveJailCard(true);

        person.setMoney(2);
        person.setLocation(30);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());
                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert person.isThreePair() == false;
                mockedClient.verify(Client::getClient);
                verify(sampleMock).sendObject(any());

            }
        }
    }

    @Test
    @Order(14)
    public void testNoJailCard() {
        Client sampleMock = mock(Client.class);
        person.setHaveJailCard(false);

        person.setMoney(2);
        person.setLocation(30);
        personDAO.changePerson(person);
        GamePanel gamePanelMock = mock(GamePanel.class);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();
        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)){
            try(MockedStatic mockedClient = mockStatic(Client.class)){
                try(MockedConstruction<GamePanel> mockedGamePanel = mockConstruction(GamePanel.class)){
                    try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                        mockedClient.when(Client::getClient).thenReturn(sampleMock);
                        mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(gamePanelMock);
                        doNothing().when(sampleMock).sendObject(any());
                        streetActions.action();
                        doNothing().when(gamePanelMock).movePieceOnePlace(anyInt(), anyInt());
                        verify(gamePanelMock).movePieceOnePlace(0, 0);
                        mockedClient.verify(Client::getClient);
                        verify(sampleMock).sendObject(any());

                }}
            }
        }
    }

    @Test
    public void testIsBuy() {
        boolean expected = true;
        StreetActions.setBuy(expected);
        boolean actual = StreetActions.isBuy();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetBuy() {
        boolean expected = true;
        StreetActions.setBuy(expected);
        assertEquals(expected, StreetActions.isBuy());
    }
}