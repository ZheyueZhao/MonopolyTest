package networkTest;

import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ServerTest {
    public Server serverSpy;
    public int port = 3000;

    @Test
    public void testGetServerWhenInstanceNull() {
        assertNotNull(Server.getServer());
    }

    @Test
    //Depends on configuration, whether or not this should be thrown - throughout the app, many errors are thrown to the
    //JOptionPane, but they didn't catch all errors - and this is one that is instead thrown to console
    public void testSendObjectWhenNotConfigured() {
        assertThrows(IndexOutOfBoundsException.class, () -> Server.getServer().sendObject(0, "Hello world"));
    }

    @Test
    public void testingRunServerBaseWorks() throws Exception {
        ServerSocket ss = new ServerSocket(port);

        try(MockedConstruction<ServerSocket> mockSocket = Mockito.mockConstruction(ServerSocket.class, (mock, context)-> {
            when(mock.accept()).thenReturn(Client.getClient().getSocket("localhost", port));
        })) {
            serverSpy = spy(Server.getServer());
            when(serverSpy.getSocket(anyInt())).thenReturn(mockSocket.constructed().get(0));
            ServerSocket sock = serverSpy.getSocket(port);
            ss.close();
            assertDoesNotThrow(()-> serverSpy.runServer(port, 1));
        }
    }

}
