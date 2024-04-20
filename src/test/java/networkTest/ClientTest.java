package networkTest;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientTest {

    public static String ip = "127.0.0.1";
    public static int port = 31416;

    public ByteArrayOutputStream out;
    public ByteArrayInputStream in;

    public static byte[] buf = new byte[100];

    public Socket socketMock;
    public Client clientSpy;


    @BeforeEach
    public void setUP() throws Exception {

    }

    @Test
    public void basicClientTest() throws Exception {
        PersonDAO.getPersonDAO().getPersons();
        boolean res = PersonDAO.getPersonDAO().checkForPerson("test", "password");

        if (!res) {
            throw new Exception("User DNE");
        }

        PersonDAO.getPersonDAO().setUserThatSignIn("test");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outp = new ObjectOutputStream(bos);
        outp.writeObject(1);
        outp.writeObject(1);
        outp.writeObject(PersonDAO.getPersonDAO().getThePerson());
        outp.flush();
        buf = bos.toByteArray();

        socketMock = mock(Socket.class);
        out = new ByteArrayOutputStream();
        in = new ByteArrayInputStream(buf);



        try(MockedConstruction<Socket> mockSocket = Mockito.mockConstruction(Socket.class, (mock, context)-> {
            when(mock.getOutputStream()).thenReturn(out);
            when(mock.getInputStream()).thenReturn(in);
        })) {
            clientSpy = spy(Client.getClient());
            when(clientSpy.getSocket(anyString(), anyInt())).thenReturn(mockSocket.constructed().get(0));
            Socket sock = clientSpy.getSocket(ip, port);

            assertDoesNotThrow(()-> clientSpy.runClient(port, ip));
        }
    }

    @Test
    public void testGetClientReturnsNotNull() {
        assertNotNull(Client.getClient());
    }

    //FAULT FOUND: doesn't catch this exception and send to JOptionPane when client hasn't been initialized
    //based on the behavior in the code, I would expect this error to be displayed in a JOptionPane similarly to
    // other errors, however it is uncaught and thrown
    @Test
    public void testSendObjectNoExceptionThrownWhenRunning() throws NoSuchFieldException {
        assertDoesNotThrow(()->Client.getClient().sendObject(new Person("username", "password")));
    }

    @Test
    public void testGetClientNo() {
        assertEquals(0, Client.getClient().getClientNo());
    }

    @Test
    public void testSetClientNo() {
        Client.getClient().setClientNo(4);
        assertEquals(4, Client.getClient().getClientNo());
        Client.getClient().setClientNo(0);
    }

    @Test
    public void testGetNumberOfPlayers() {
        assertEquals(0, Client.getClient().getNumberOfPlayers());
    }

    @Test
    public void testSetNumberOfPlayers() {
        Client.getClient().setNumberOfPlayers(4);
        assertEquals(4, Client.getClient().getNumberOfPlayers());
        Client.getClient().setNumberOfPlayers(0);
    }
}
