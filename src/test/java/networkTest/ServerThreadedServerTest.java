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

public class ServerThreadedServerTest {
    private Server serverSpy;
    private int port = 3000;
    private int sleepTime = 10000;
    private final Thread currentThread = Thread.currentThread();

    @BeforeEach
    public void setup() {
        PersonDAO.getPersonDAO().getPersons();
        PlayerDAO.getPlayerDAO().getPlayers();
        EstateDAO.getEstateDAO().getEstates();
        PersonDAO.getPersonDAO().addPerson(new Person("username", "password"));
        PersonDAO.getPersonDAO().setUserThatSignIn("username");

<<<<<<< HEAD
    @Test
    public void test() throws IOException {
        ServerSocket ss = new ServerSocket(port + 1);

        try(MockedConstruction<ServerSocket> mockSocket = Mockito.mockConstruction(ServerSocket.class, (mock, context)-> {
            when(mock.accept()).thenReturn(Client.getClient().getSocket("localhost", port + 1));
        })) {
            serverSpy = spy(Server.getServer());
            when(serverSpy.getSocket(anyInt())).thenReturn(mockSocket.constructed().get(0));
            ServerSocket sock = serverSpy.getSocket(port + 1);

            assertDoesNotThrow(()-> serverSpy.runServer(port + 1, 1));

            assertDoesNotThrow(()-> serverSpy.sendObject(0, "hello world"));
        }
    }

    /*
    @Test
    public void testMaybe() {
=======
>>>>>>> ae6505f3b75d54bec05f54113e315cd4e1485e49
        Thread th = new Thread(new TestingServer());
        th.start();
        Thread re = new Thread(new TestingClient());
        re.start();
    }

    @Test
    public void testGameStartClientServerInteractionDoesNotFail() {
        PersonDAO.getPersonDAO().setUserThatSignIn("username");
        Person p = PersonDAO.getPersonDAO().getThePerson();
        Server.getServer().sendClintNo();
        Server.getServer().sendObjectToAll(1);
        PersonDAO.getPersonDAO().changePerson(p);
        PlayerDAO.getPlayerDAO().changeOnePlayer(p);
        Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
        assertDoesNotThrow(()-> Client.getClient().sendObject(data));
    }
*/
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
