package uiTest;
import org.bihe.ui.*;
import org.bihe.ui.actionPanel.ActionPanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.mainFrame.EnterGamePanel;
import org.junit.jupiter.api.*;
import org.mockito.MockedConstruction;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;

public class GUIManagerTest {

    @Test
    public void testGetGameFrame() {
        try(MockedConstruction<GameFrame> mockedGameFrame = mockConstruction(GameFrame.class)){
            assertNotNull(GUIManager.getGameFrame());
        }
    }

    @Test
    public void testGetGamePanel() {
        assertNotNull(GUIManager.getGamePanel());
    }

    @Test
    public void testGetJoinGamePanel() {
        assertNotNull(GUIManager.getJoinGamePanel());
    }

    @Test
    public void testGetLoginGamePanel() {
        assertNotNull(GUIManager.getLoginPanel());
    }

    @Test
    public void testGetMainFrame() {
        assertNotNull(GUIManager.getMainFrame());
    }

    @Test
    public void testGetSendRequest() {
        assertNotNull(GUIManager.getSendRequestFrame());
    }

    @Test
    public void testGetSendRequesPanelt() {
        try(MockedConstruction<SendRequestPanel> mockedSendRequestPanel = mockConstruction(SendRequestPanel.class)){
            assertNotNull(GUIManager.getSendRequestPanel());
        }
    }

    @Test
    public void testGetSignIn() {
        assertNotNull(GUIManager.getSignInPanel());
    }

    @Test
    public void testGetSignUp() {
        assertNotNull(GUIManager.getSignUpPanel());
    }

    @Test
    public void testGetAction() {
        try(MockedConstruction<ActionPanel> mockedActionPanel = mockConstruction(ActionPanel.class)){
            assertNotNull(GUIManager.getActionPanel());
        }
    }

    @Test
    public void testGetButton() {
        assertNotNull(GUIManager.getButtonPanel());
    }

    @Test
    public void testGetDice() {
        assertNotNull(GUIManager.getDicePanel());
    }

    @Test
    public void testGetPlay() {
        try(MockedConstruction<PlayerPanel> mockedPlayerPanel = mockConstruction(PlayerPanel.class)){
            assertNotNull(GUIManager.getPlayerPanel());
        }
    }

    @Test
    public void testGetEstate() {
        try(MockedConstruction<EstatesPanel> mockedEstatesPanel = mockConstruction(EstatesPanel.class)){
            assertNotNull(GUIManager.getEstatePanel());
        }
    }

    @Test
    public void testGetBuyStreet() {
        assertNotNull(GUIManager.getBuyStreetDialog());
    }

    @Test
    public void testMakeNewGamePanel() {
        assertNotNull(GUIManager.getMakeNewGamePanel());
    }

    @Test
    public void testEnterGamePanel() {
        try(MockedConstruction<EnterGamePanel> mockedEnterGamePanel = mockConstruction(EnterGamePanel.class)){
            assertNotNull(GUIManager.getEnterGamePanel());
        }
    }

    @Test
    public void testGetRequestDialog() {
        assertNotNull(GUIManager.getRequestDialog());
    }

    @Test
    public void testGetOutOfJailDialog() {
        assertNotNull(GUIManager.getGetOutOfJailDialog());
    }

}