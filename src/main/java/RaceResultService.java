import java.util.Collection;
import java.util.HashSet;

public class RaceResultService {
    private Collection<Client> clients = new HashSet<Client>();

    public RaceResultService(Collection<Client> clients) {
        this.clients = clients;
    }

    public void addSubscriber(Client client){
        clients.add(client);
    }

    public void send(Message message){
        if (clients.size() < 1 ) {
            return;
        }
        for (Client client: clients){
            client.receive(message);
        }
    }

    public void removeSubscriber(Client client){
        clients.remove(client);
    }
}