package modelTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jdk.jshell.execution.Util;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.*;
import org.bihe.network.client.Client;
import org.bihe.network.client.testClient;
import org.bihe.network.server.Server;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.*;

import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;


import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
                assert !person.getEstates().contains(1);
            }
        }
    }

    @Test
    @Order(2)
    public void testPurchaseMoney() {
        Client sampleMock = mock(Client.class);
        person.setMoney(99999);
        person.setLocation(1);
        personDAO.changePerson(person);
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();
                assert testEstates.get(1).isOwned();
            }
        }
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

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert testEstates.get(5).isOwned();
                assert testEstates.get(5).getOwner().equals(person.getUserName());
            }
        }
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

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();
                assert testEstates.get(12).isOwned();
                assert testEstates.get(12).getOwner().equals(person.getUserName());
            }
        }
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
            try(MockedConstruction<CommunityChest> mockedChance = mockConstruction(CommunityChest.class)){
                //Chance chanceMock = new Chance();
                mockedClient.when(Client::getClient).thenReturn(sampleMock);

                doNothing().when(sampleMock).sendObject(any());

                streetActions.action();
                CommunityChest communityChestMock = mockedChance.constructed().get(0);
                verify(communityChestMock).communityChest();
            }
        }
    }

}