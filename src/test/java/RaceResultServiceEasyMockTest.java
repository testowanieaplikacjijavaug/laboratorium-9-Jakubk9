import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.easymock.EasyMock.*;

public class RaceResultServiceEasyMockTest {
    private Client client;
    private Message message;
    private RaceResultService raceResultService;
    private Collection<Client> clients;

    @BeforeEach
    public void setUp() {
        client = mock(MockType.NICE, Client.class);
        message = mock(MockType.NICE, Message.class);
        clients = mock(MockType.NICE, Collection.class);
        raceResultService = new RaceResultService(clients);
    }

    @Test
    public void sendTest() {
        replay(client);

        raceResultService.send(message);

        verify(client);
    }

    @Test
    public void addSubscriberTest() {
        replay(client);

        raceResultService.addSubscriber(client);

        verify(client);
    }

    @Test
    public void removeSubscriberTest() {
        replay(client);

        raceResultService.removeSubscriber(client);

        verify(client);
    }

    @AfterEach
    public void tearDown() {
        client = null;
        message = null;
        clients = null;
        raceResultService = null;
    }
}
