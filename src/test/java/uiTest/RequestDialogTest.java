package uiTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.*;

import org.bihe.model.Estate;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.junit.jupiter.api.*;

import org.bihe.model.Request;
import org.bihe.ui.RequestDialog;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
class ConcreteEstateUI extends Estate {
    public ConcreteEstateUI(String name, int panelNo, int price, int rent, int mortgage, int unMortgage) {
        super(name, panelNo, price, rent, mortgage, unMortgage);
    }
}

public class RequestDialogTest {

    private RequestDialog requestDialog;
    private Request request;
    private ArrayList<Estate> giveEstates = new ArrayList<>();
    private ArrayList<Estate> recieveEstates = new ArrayList<>();
    private Request testRequest;

    @BeforeEach
    public void setUp() {
        requestDialog = new RequestDialog();
        request = mock(Request.class);

    }

    @Test
    public void setRequest() {
        try(MockedConstruction<JLabel> mockedJLabel = mockConstruction(JLabel.class)) {
                JScrollPane mockScroll = mock(JScrollPane.class);
                when(mockScroll.getViewport()).thenReturn(new JViewport());

                Field scrollField = RequestDialog.class.getDeclaredField("scroll");
                scrollField.setAccessible(true);
                JScrollPane scroll = (JScrollPane) scrollField.get(requestDialog);
                scrollField.set(requestDialog, mockScroll);

                requestDialog.setRequest(request);
                JLabel JLabelMock = mockedJLabel.constructed().get(0);
                verify(JLabelMock, times(1)).setPreferredSize(any());
                verify(JLabelMock, times(1)).setFont(any());

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void setYesAction() {
        Estate estate = new ConcreteEstateUI("Test Estate", 1, 200, 20, 100, 120);
        Estate estate2 = new ConcreteEstateUI("Test Estate2", 2, 200, 20, 100, 120);
        giveEstates.add(estate);
        recieveEstates.add(estate2);
        testRequest = new Request(giveEstates,recieveEstates,0,0,"testUser","owner");

        Client sampleMock = mock(Client.class);
        try(MockedStatic mockedClient = mockStatic(Client.class)){
            mockedClient.when(Client::getClient).thenReturn(sampleMock);
            doNothing().when(sampleMock).sendObject(any());
            RequestDialog requestDialogTest = GUIManager.getRequestDialog();
            requestDialogTest.setRequest(testRequest);

            Field ButtonField = RequestDialog.class.getDeclaredField("yesButton");
            ButtonField.setAccessible(true);
            JButton yesButton = (JButton) ButtonField.get(requestDialogTest);
            ButtonField.set(requestDialogTest, yesButton);
            yesButton.doClick();
            assertEquals("testUser", testRequest.getReceiver());
            assertEquals("owner",testRequest.getSender());
            assertEquals(1,testRequest.getResponse());

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void setNoAction() {
        Estate estate = new ConcreteEstateUI("Test Estate", 1, 200, 20, 100, 120);
        Estate estate2 = new ConcreteEstateUI("Test Estate2", 2, 200, 20, 100, 120);
        giveEstates.add(estate);
        recieveEstates.add(estate2);
        testRequest = new Request(giveEstates,recieveEstates,0,0,"testUser","owner");

        Client sampleMock = mock(Client.class);
        try(MockedStatic mockedClient = mockStatic(Client.class)){
            mockedClient.when(Client::getClient).thenReturn(sampleMock);
            doNothing().when(sampleMock).sendObject(any());
            RequestDialog requestDialogTest = GUIManager.getRequestDialog();
            requestDialogTest.setRequest(testRequest);

            Field ButtonField = RequestDialog.class.getDeclaredField("noButton");
            ButtonField.setAccessible(true);
            JButton yesButton = (JButton) ButtonField.get(requestDialogTest);
            ButtonField.set(requestDialogTest, yesButton);
            yesButton.doClick();
            assertEquals("testUser", testRequest.getReceiver());
            assertEquals("owner",testRequest.getSender());
            assertEquals(-1,testRequest.getResponse());

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}