package networkTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerThreadedServerTest {
    private int port = 3000;

    @BeforeEach
    public void setup() {
        PersonDAO.getPersonDAO().getPersons();
        PlayerDAO.getPlayerDAO().getPlayers();
        EstateDAO.getEstateDAO().getEstates();
        PersonDAO.getPersonDAO().addPerson(new Person("username", "password"));
        PersonDAO.getPersonDAO().setUserThatSignIn("username");

        Thread th = new Thread(new TestingServer());
        th.start();
        Thread re = new Thread(new TestingClient());
        re.start();
    }

    @Test
    public void testGameStartClientServerInteractionDoesNotFail() {
        PersonDAO.getPersonDAO().setUserThatSignIn("username");
        Person p = PersonDAO.getPersonDAO().getThePerson();

        assertDoesNotThrow(()-> Server.getServer().sendClintNo());
        assertDoesNotThrow(()-> Server.getServer().sendObjectToAll(1));

        PersonDAO.getPersonDAO().changePerson(p);
        PlayerDAO.getPlayerDAO().changeOnePlayer(p);
        Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates(), 1, 1, 2);

        assertDoesNotThrow(()-> Client.getClient().sendObject(data));
    }

    private class TestingServer implements Runnable {
        public void run() {
            Server.getServer().runServer(3000, 1);
        }
    }

    private class TestingClient implements Runnable {
        public void run() {
            Client.getClient().runClient(3000, "localhost");
        }
    }

}
