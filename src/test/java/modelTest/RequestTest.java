package modelTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.Request;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.RequestDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;


import static org.mockito.Mockito.verify;

public class RequestTest {
    private static final long serialVersionUID = 5064072459813802833L;

    private ArrayList<Estate> giveEstates = new ArrayList<>();
    private ArrayList<Estate> recieveEstates = new ArrayList<>();
    private int giveMoney;
    private int recieveMoney;
    private String sender;
    private String receiver;
    private int response;
    private Request testRequest;

    @BeforeEach
    public void setUp(){
        // guiManagerMock = mock(GUIManager.class);
        Estate estate = new ConcreteEstate("Test Estate", 1, 200, 20, 100, 120);
        Estate estate2 = new ConcreteEstate("Test Estate2", 2, 200, 20, 100, 120);
        giveEstates.add(estate);
        recieveEstates.add(estate2);
        testRequest = new Request(giveEstates,recieveEstates,0,0,"testUser","owner");

      //  (ArrayList<Estate> giveEstates, ArrayList<Estate> recieveEstates, int giveMoney, int recieveMoney,
      //  String sender, String receiver)
    }

    @Test
    @Order(2)
    public void testPurchaseNoMoney() {
        response = 0;
        RequestDialog mockRequestDialog = mock(RequestDialog.class);
        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)){
                mockedGUIManager.when(GUIManager::getRequestDialog).thenReturn(mockRequestDialog);
                testRequest.analyseRequest();
                mockedGUIManager.verify(GUIManager::getRequestDialog, times(2));


        }
    }

    @Test
    @Order(1)
    public void testGettersAndSetters() {
        assertEquals(recieveEstates, testRequest.getRecieveEstates());
        assertEquals(giveEstates, testRequest.getGiveEstates());
        assertEquals(0, testRequest.getGiveMoney());
        assertEquals(0, testRequest.getRecieveMoney());

        testRequest.setReceiver("");
        assertEquals("", testRequest.getReceiver());

        testRequest.setSender("");
        assertEquals("", testRequest.getSender());

        testRequest.setResponse(0);
        assertEquals(0, testRequest.getResponse());
    }

    /*
    @Test
    public void testRequestWith1Response() {
        testRequest = new Request(giveEstates, recieveEstates, 50, 50, "testUser", "testUser");
        testRequest.setResponse(1);
        PersonDAO.getPersonDAO().getPersons();
        PersonDAO.getPersonDAO().setUserThatSignIn("testUser");
        PlayerDAO.getPlayerDAO().changePlayerDAO(PersonDAO.getPersonDAO().getPersons());

        Client sampleMock = mock(Client.class);

        try(MockedStatic mockedJOptionPane = mockStatic(JOptionPane.class)) {
            try(MockedStatic mockedClient = mockStatic(Client.class)) {
                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                doNothing().when(sampleMock).sendObject(any());
                testRequest.analyseRequest();
            }
        }
    }
*/

    @Test
    public void testRequestWithNegativeOneResponse() {
        try(MockedStatic mockedJOptionPane = mockStatic(JOptionPane.class)) {
            testRequest = new Request(giveEstates, recieveEstates, 50, 50, "testUser", "testUser");
            testRequest.setResponse(-1);
            testRequest.analyseRequest();

        }
    }

    @Test
    public void testRequestWithOtherResponse() {
        testRequest.setResponse(-5);
        assertDoesNotThrow(()->testRequest.analyseRequest());
    }

}
