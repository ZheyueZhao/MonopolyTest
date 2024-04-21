package networkTest;

import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ServerTest {
    public Server serverSpy;
    public int port = 6666;

    @Test
    public void testing() throws Exception {
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
