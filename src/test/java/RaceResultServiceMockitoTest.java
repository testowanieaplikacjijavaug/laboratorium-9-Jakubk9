import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RaceResultServiceMockitoTest {
    private Client client;
    private Message message;
    private RaceResultService raceResultService;
    private Collection<Client> clients;

    @BeforeEach
    public void setUp() {
        client = mock(Client.class);
        message = mock(Message.class);
        clients = spy(new HashSet<>());
        raceResultService = new RaceResultService(clients);
    }

    @Test
    public void addSubscriberTest() {
        raceResultService.addSubscriber(client);

        assertEquals(1, clients.size());

        verify(clients).add(client);
    }

    @Test
    public void sendTest() {
        clients.add(client);

        raceResultService.send(message);

        verify(client).receive(message);
    }

    @Test
    public void removeSubscriberTest() {
        raceResultService.addSubscriber(client);
        raceResultService.removeSubscriber(client);

        assertEquals(0,clients.size());
        verify(clients).remove(client);
    }

    @AfterEach
    public void tearDown() {
        client = null;
        message = null;
        clients = null;
        raceResultService = null;
    }
}
