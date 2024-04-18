package modelTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Request;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.RequestDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

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
import org.bihe.ui.GamePanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.*;

import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;


import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    @Order(1)
    public void testPurchaseNoMoney() {
        response = 0;
        RequestDialog mockRequestDialog = mock(RequestDialog.class);
        try(MockedStatic mockedGUIManager = mockStatic(GUIManager.class)){
                mockedGUIManager.when(GUIManager::getRequestDialog).thenReturn(mockRequestDialog);
                testRequest.analyseRequest();
                mockedGUIManager.verify(GUIManager::getRequestDialog, times(2));


        }
    }
}
