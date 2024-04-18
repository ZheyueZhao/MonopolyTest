package NetworkTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class ClientTest {

    public static String ip = "";
    public static int port = 3000;

    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;

    public Thread threadMock;

    public Socket socketMock;


    @BeforeEach
    public void setUP() throws IOException {
        ip = InetAddress.getLocalHost().getHostAddress().trim();
        socketMock = mock(Socket.class);
        when(socketMock.getOutputStream()).thenReturn(outputStream);
        when(socketMock.getInputStream()).thenReturn(inputStream);
        threadMock = mock(Thread.class);
        doNothing().when(threadMock).start();
    }

    @Test
    public void testing() {

    }
}
