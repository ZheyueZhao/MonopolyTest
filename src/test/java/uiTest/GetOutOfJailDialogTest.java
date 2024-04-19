package uiTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GetOutOfJailDialog;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class GetOutOfJailDialogTest {

    @Test
    public void setYesAction() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person("Joseph", "980428",1,100);
        PersonDAO personDAO = PersonDAO.getPersonDAO();
        personDAO.removePerson(person);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("Joseph");
        person.goToJail();
        person.setMoney(person.newMoney(500));
        person.getMoney();
        GetOutOfJailDialog getOutOfJailDialogTest = GUIManager.getGetOutOfJailDialog();

        Field ButtonField = GetOutOfJailDialog.class.getDeclaredField("yesButton");
        ButtonField.setAccessible(true);
        JButton yesButton = (JButton) ButtonField.get(getOutOfJailDialogTest);
        ButtonField.set(getOutOfJailDialogTest, yesButton);

        yesButton.doClick();
        assert personDAO.getThePerson().getJail() == 0;
    }

    @Test
    public void setNoAction() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person("Joseph", "980428",1,0);
        PersonDAO personDAO = PersonDAO.getPersonDAO();
        personDAO.removePerson(person);
        personDAO.addPerson(person);
        personDAO.setUserThatSignIn("Joseph");
        person.goToJail();
        person.setMoney(person.newMoney(500));
        person.getMoney();
        GetOutOfJailDialog getOutOfJailDialogTest = GUIManager.getGetOutOfJailDialog();

        Field ButtonField = GetOutOfJailDialog.class.getDeclaredField("noButton");
        ButtonField.setAccessible(true);
        JButton noButton = (JButton) ButtonField.get(getOutOfJailDialogTest);
        ButtonField.set(getOutOfJailDialogTest, noButton);

        noButton.doClick();
        assert personDAO.getThePerson().getJail() == 3;
        assert personDAO.getThePerson().getMoney() == 500;
    }
}
