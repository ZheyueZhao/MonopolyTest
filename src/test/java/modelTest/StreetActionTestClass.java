package modelTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.*;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreetActionTestClass {
    private static Map<String, Estate> testEstates;
    private Estate estate;
    private PersonDAO personDAO;
    private Person person;
    private EstateDAO estateDAO;
    private PlayerDAO playerDAO;
    private Client clientMock;
    private Server server;
    private StreetActions streetActions = new StreetActions();
    @Test
    @Order(1)
    public void testPurchaseNoMoney() {
        // Redirect System.out to capture the output
        DisplayMessage displayMessageMock = mock(DisplayMessage.class);
        clientMock = mock(Client.class);
        person = new Person("testUser", "password");

        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        person.setLocation(1);
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        personDAO.changePerson(person);
        estateDAO = EstateDAO.getEstateDAO();
        estateDAO.getEstates();

        streetActions.setDisplayMessage(displayMessageMock);
        streetActions.setClient(clientMock);
        doNothing().when(clientMock).sendObject(any());
        when(clientMock.getClientInstance()).thenReturn(clientMock);
        streetActions.action();
        verify(displayMessageMock, times(1)).getValueFromDialog("You don't have enough money to buy this Street");

    }

    @Test
    @Order(2)
    public void testPurChaseMoney() {
        // Redirect System.out to capture the output
        DisplayMessage displayMessageMock = mock(DisplayMessage.class);
        clientMock = mock(Client.class);
        person = new Person("testUser", "password");

        personDAO = PersonDAO.getPersonDAO();
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("testUser");
        personDAO.getPersons();
        person = personDAO.getThePerson();
        person.setLocation(1);
        person.setMoney(99999);
        playerDAO = PlayerDAO.getPlayerDAO();
        playerDAO.getPlayers();
        personDAO.changePerson(person);
        estateDAO = EstateDAO.getEstateDAO();
        Map<Integer, Estate> testEstates = estateDAO.getEstates();

        streetActions.setDisplayMessage(displayMessageMock);
        streetActions.setClient(clientMock);
        doNothing().when(clientMock).sendObject(any());
        when(clientMock.getClientInstance()).thenReturn(clientMock);
        streetActions.action();
        Estate myEstate = testEstates.get(1);
        assert myEstate.isOwned();
        assert myEstate.getOwner().equals("testUser");
        person.setMoney(0);
    }

}