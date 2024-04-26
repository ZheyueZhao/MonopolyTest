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
                estateDAO.changeEstateDAO(estates);
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
        estateDAO.changeEstateDAO(estates);
        person.setMoney(2);
        person.setLocation(12);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){
                try(MockedStatic mockedEstateDAO = mockStatic(EstateDAO.class)) {
                    mockedEstateDAO.when(EstateDAO::getEstateDAO).thenReturn(estateDAO);
                    mockedClient.when(Client::getClient).thenReturn(sampleMock);
                    doNothing().when(sampleMock).sendObject(any());
                    streetActions.action();
                    assert !testEstates.get(12).isOwned();
                    assert !person.getEstates().contains(12);
                    mockedClient.verify(Client::getClient);
                    verify(sampleMock).sendObject(any());
                }
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