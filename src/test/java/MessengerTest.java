import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class MessengerTest {
    private Client client;
    private MailServer mailServer;
    private Messenger messenger;
    private Template template;
    private TemplateEngine templateEngine;

    @Test
    public void sendMsgTest() {
        client = mock(Client.class);
        mailServer = mock(MailServer.class);
        template = mock(Template.class);
        templateEngine = mock(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);

        String msg = "msg";
        String email = "email@gmail.com";

        when(client.getEmail()).thenReturn(email);
        when(templateEngine.prepareMessage(template, client)).thenReturn(msg);

        messenger.sendMessage(client, template);

        verify(templateEngine).prepareMessage(template, client);
        verify(mailServer).send(email, msg);
    }

    @Test
    public void nullClientShouldThrowsExceptionTest() {
        client = mock(Client.class);
        mailServer = mock(MailServer.class);
        template = mock(Template.class);
        templateEngine = mock(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);

        String msg = "msg";

        when(templateEngine.prepareMessage(template,null)).thenReturn(msg);

        assertThatThrownBy(() -> messenger.sendMessage(null,template)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void sendMsgButInsteadOfUsingMockWeUsedSpyTest() {
        client = spy(Client.class);
        mailServer = spy(MailServer.class);
        template = spy(Template.class);
        templateEngine = spy(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);

        String msg = "msg";
        String email = "email@gmail.com";

        when(client.getEmail()).thenReturn(email);
        when(templateEngine.prepareMessage(template, client)).thenReturn(msg);

        messenger.sendMessage(client, template);

        verify(templateEngine).prepareMessage(template, client);
        verify(mailServer).send(email, msg);
    }

    @Test
    public void sendingMsgForNullEmailTest() {
        client = spy(Client.class);
        mailServer = spy(MailServer.class);
        template = spy(Template.class);
        templateEngine = spy(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);

        String msg = "msg";

        when(client.getEmail()).thenReturn(null);
        when(templateEngine.prepareMessage(template, client)).thenReturn(msg);

        messenger.sendMessage(client, template);

        verify(mailServer).send(null,msg);
    }
}
