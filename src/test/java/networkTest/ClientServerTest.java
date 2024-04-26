package networkTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClientServerTest {
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
    //sometimes will not pass when the entire suite is running, due to deep dependencies on other classes - mainly the
    // DAOs and GUI
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
