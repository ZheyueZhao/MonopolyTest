package modelTest;
import org.bihe.model.Exit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExitTest {

    @Test
    public void testGetUserName() {
        String userName = "testUser";
        Exit exit = new Exit(userName);
        assertEquals(userName, exit.getUserName());
    }

    @Test
    public void testSetUserName() {
        Exit exit = new Exit("");
        String userName = "newUser";
        exit.setUserName(userName);
        assertEquals(userName, exit.getUserName());
    }
}