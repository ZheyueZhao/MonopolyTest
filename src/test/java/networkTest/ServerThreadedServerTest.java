package networkTest;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Exit;
import org.bihe.model.Request;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.GUIManager;
import org.bihe.ui.mainFrame.SignInPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.SocketException;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ServerThreadedServerTest {
    private Server serverSpy;
    private int port = 3000;
    private int sleepTime = 10000;
    private final Thread currentThread = Thread.currentThread();

    @BeforeAll
    public static void setup() {
        PersonDAO.getPersonDAO().getPersons();
        PlayerDAO.getPlayerDAO().getPlayers();
        EstateDAO.getEstateDAO().getEstates();
        PersonDAO.getPersonDAO().setUserThatSignIn("username");
    }

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

    @Test
    public void testMaybe() {
        Thread th = new Thread(new TestingServer());
        th.start();
        Client.getClient().runClient(3000, "localhost");
        Client.getClient().sendObject(new Exit("username"));
    }

    private class TestingServer implements Runnable {
        public void run() {
            Server.getServer().runServer(3000, 1);
        }
    }

}
