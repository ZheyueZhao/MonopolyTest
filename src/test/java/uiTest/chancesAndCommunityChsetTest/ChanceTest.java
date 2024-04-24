package uiTest.chancesAndCommunityChsetTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.Generated;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.PieceWorker;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class ConcreteEstateChances extends Estate {
    public ConcreteEstateChances(String name, int panelNo, int price, int rent, int mortgage, int unMortgage) {
        super(name, panelNo, price, rent, mortgage, unMortgage);
    }
    // Implement constructors and methods of ConcreteEstate
}

@Generated
public class ChanceTest {
    @Test
    public void setYesAction() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Chance chanceTest = new Chance();
        Chance spyObj = spy(chanceTest);

        Class<?> pieceWorkerTestClass = chanceTest.getClass();
        Method chanceMethod = pieceWorkerTestClass.getDeclaredMethod("makeRandom");
        chanceMethod.setAccessible(true);
        chanceMethod.invoke(chanceTest);
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < 30000; i++) {

            int randomNumber = (int) chanceMethod.invoke(chanceTest);
            frequencyMap.put(randomNumber, frequencyMap.getOrDefault(randomNumber, 0) + 1);
        }

        for (int number = 1; number < 7; number++) {
            int frequency = frequencyMap.getOrDefault(number, 0);
            for(int num=1; num<7;num++){
                int alterFrequency = frequencyMap.getOrDefault(number, 0);
                assert frequency <= alterFrequency*1.5 && frequency >= alterFrequency*0.5;
            }

        }

    }

    //This test is functional and achieves coverage but it is a bad test. so it is currently disabled until i figure oout an improvement
    @Disabled
    @RepeatedTest(100)
    public void chanceOne() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Client sampleMock = mock(Client.class);
        Chance chanceTest = new Chance();
        PersonDAO testPersonDAO = PersonDAO.getPersonDAO();
        testPersonDAO.getPersons();
        testPersonDAO.setUserThatSignIn("john");
        Estate estate = new ConcreteEstateChances("Test Estate", 1, 200, 20, 100, 120);
        ArrayList<Estate> estates = new ArrayList<>();
        estates.add(estate);
        testPersonDAO.getThePerson().setEstates(estates);
        GamePanel mockGamePanel = Mockito.mock(GamePanel.class);
        PersonDAO mockPersonDAO = Mockito.mock(PersonDAO.class);
        Person mockperson = Mockito.mock(Person.class);
        assertDoesNotThrow(() -> {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                try (MockedStatic mockedGUIManager = mockStatic(GUIManager.class)) {
                    try (MockedStatic mockedPersondao = mockStatic(PersonDAO.class)) {
                        try(MockedConstruction<StreetActions> mockedStreetActions = mockConstruction(StreetActions.class)){
                            try(MockedStatic mockedClient = mockStatic(Client.class)) {
                                mockedGUIManager.when(GUIManager::getGamePanel).thenReturn(mockGamePanel);
                                mockedGUIManager.when(PersonDAO::getPersonDAO).thenReturn(mockPersonDAO);
                                mockedClient.when(Client::getClient).thenReturn(sampleMock);
                                when(mockGamePanel.distance(anyInt(), anyInt())).thenReturn(100);
                                when(mockPersonDAO.getThePerson()).thenReturn(mockperson);
                                when(mockperson.getLocation()).thenReturn(100);
                                doNothing().when(mockGamePanel).movePieceOnePlace(anyInt(), anyInt());
                                doNothing().when(mockPersonDAO).changePerson(any());
                                chanceTest.chance();

                            }
                    }}
                }
            }
        });



    }

}
