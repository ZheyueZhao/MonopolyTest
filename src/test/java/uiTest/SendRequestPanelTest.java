package uiTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.SendRequestFrame;
import org.bihe.ui.SendRequestPanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SendRequestPanelTest {
    @Test
    public void testSendRequestPanel() {
        PlayerDAO playerDAO = PlayerDAO.getPlayerDAO();
        Person person = new Person("testUser", "password");
        Estate estate = new ConcreteEstateUI("Test Estate", 1, 200, 20, 100, 120);
        ArrayList<Estate> testEstates = new ArrayList<>();
        testEstates.add(estate);

        person.getEstates();
        playerDAO.addPlayer(person);
        PersonDAO personDAO = PersonDAO.getPersonDAO();
        personDAO.getPersons();
        personDAO.setUserThatSignIn("testUser");
        PlayerPanel mockPlayerPanel = mock(PlayerPanel.class);
        assertDoesNotThrow(() -> {
            try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                mockedGUIManager.when(GUIManager::getPlayerPanel).thenReturn(mockPlayerPanel);
                mockedGUIManager.when(GUIManager::getEstatePanel).thenReturn(mock(EstatesPanel.class));
                person.setEstates(testEstates);
                when(mockPlayerPanel.getPlayerUserName()).thenReturn("testUser");
                SendRequestPanel requestPanel = new SendRequestPanel();

            }
        });
    }
}
