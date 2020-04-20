import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoTest {

    @Mock
    private FriendsCollection friends;

    @InjectMocks
    private FriendshipsMongo friendships;

    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        when(friends.findByName("Joe")).thenReturn(joe);

        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = Mockito.mock(Person.class);

        when(friends.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(expected);

        assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");

        verify(friends).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void JoeNameIsJoe() {
        Person joe = mock(Person.class);
        when(joe.getName()).thenReturn("Joe");

        assertThat(joe.getName()).isEqualTo("Joe");
        verify(joe).getName();
    }

    @Test
    public void setEmptyNameShouldThrowAnExceptionTest() {
        Person joe = mock(Person.class);
        doThrow(new IllegalArgumentException()).when(joe)
                .setName(anyString());

        assertThatThrownBy(() -> joe.setName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addEmptyFriendShouldThrowAnException() {
        Person joe = mock(Person.class);
        doThrow(new IllegalArgumentException()).when(joe)
                .addFriend(anyString());

        assertThatThrownBy(() -> joe.addFriend("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void joeAndAlexAreFriends() {
        Person joe = mock(Person.class);
        when(friends.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(Collections.singletonList("Alex"));

        assertThat(friendships.areFriends("Joe", "Alex")).isTrue();
        verify(friends).findByName("Joe");
        verify(joe).getFriends();
    }
}
