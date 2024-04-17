package modelTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.*;
import org.bihe.network.client.Client;
import org.bihe.network.client.testClient;
import org.bihe.network.server.Server;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.mockito.MockedStatic;


import javax.swing.*;
import java.io.IOException;
import java.util.Map;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreetActionTestClass {
    private static Map<String, Estate> testEstates;
    private Estate estate;
    private PersonDAO personDAO;
    private Person person;
    private Person person2;
    private EstateDAO estateDAO;
    private PlayerDAO playerDAO;
    private Client clientMock;
    private Server server;
    private StreetActions streetActions = new StreetActions();
    @Test
    @Order(1)
    public void testPurchaseNoMoney() {
        person = new Person("testUser", "password");
        Client sampleMock = mock(Client.class);
        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        person.setMoney(2);
        person.setLocation(1);
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        personDAO.changePerson(person);
        estateDAO = EstateDAO.getEstateDAO();
        Map<Integer, Estate> testEstates = estateDAO.getEstates();



        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert !testEstates.get(1).isOwned();
            }
        }
    }
    @Test
    @Order(2)
    public void testPurchaseMoney() {
        person = new Person("testUser", "password");
        Client sampleMock = mock(Client.class);
        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        person.setMoney(99999);
        person.setLocation(1);
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        personDAO.changePerson(person);
        estateDAO = EstateDAO.getEstateDAO();
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        try(MockedStatic mockedClient = mockStatic(Client.class)){
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)){

                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());

                JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
                streetActions.action();
                assert testEstates.get(1).isOwned();
            }
        }
    }

    @Test
    @Order(3)
    public void testPurchaseMoneyAlreadyOwned() {
        person = new Person("testUser", "password");
        person2 = new Person("owner","password");
        Client sampleMock = mock(Client.class);
        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.addPerson(person2);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        person.setMoney(99999);
        person2.setMoney(0);
        person.setLocation(1);
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        playerDAO.addPlayer(person);
        playerDAO.addPlayer(person2);
        personDAO.changePerson(person);
        estateDAO = EstateDAO.getEstateDAO();
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
            }
        }
    }

}