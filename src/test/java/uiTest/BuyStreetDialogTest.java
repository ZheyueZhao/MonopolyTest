package uiTest;

import org.bihe.model.StreetActions;
import org.bihe.ui.BuyStreetDialog;
import org.bihe.ui.GUIManager;
import org.bihe.ui.RequestDialog;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;

public class BuyStreetDialogTest {
    private BuyStreetDialog buyStreetDialog;

    @Test
    public void setYesAction() throws NoSuchFieldException, IllegalAccessException {
        buyStreetDialog = new BuyStreetDialog();

        BuyStreetDialog buyStreetDialogTest = GUIManager.getBuyStreetDialog();

        Field ButtonField = BuyStreetDialog.class.getDeclaredField("yesButton");
        ButtonField.setAccessible(true);
        JButton yesButton = (JButton) ButtonField.get(buyStreetDialogTest);
        ButtonField.set(buyStreetDialogTest, yesButton);

        yesButton.doClick();

        assert StreetActions.isBuy() == true;
    }

    @Test
    public void setNoAction() throws NoSuchFieldException, IllegalAccessException {
        buyStreetDialog = new BuyStreetDialog();

        BuyStreetDialog buyStreetDialogTest = GUIManager.getBuyStreetDialog();

        Field ButtonField = BuyStreetDialog.class.getDeclaredField("noButton");
        ButtonField.setAccessible(true);
        JButton noButton = (JButton) ButtonField.get(buyStreetDialogTest);
        ButtonField.set(buyStreetDialogTest, noButton);

        noButton.doClick();

        assert StreetActions.isBuy() == false;
    }

}
