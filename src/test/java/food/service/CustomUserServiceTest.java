package food.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import food.domain.User;
import food.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CustomUserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private CustomUserService customUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveToken() {
        String username = "testUser";
        String token = "testToken";
        User user = new User();
        user.setUsername(username);
        when(userRepositoryMock.findUserByUsername(username)).thenReturn(user);

        customUserService.saveToken(username, token);

        verify(userRepositoryMock, times(1)).save(user);
        assertEquals(token, user.getToken());
    }

    @Test
    public void testGetTokenByUsername() {
        String username = "testUser";
        String token = "testToken";
        User user = new User();
        user.setUsername(username);
        user.setToken(token);
        when(userRepositoryMock.findUserByUsername(username)).thenReturn(user);

        String retrievedToken = customUserService.getTokenByUsername(username);

        assertEquals(token, retrievedToken);
    }

    @Test
    public void testGetUserByToken() {
        String token = "testToken";
        User user = new User();
        when(userRepositoryMock.findUserByToken(token)).thenReturn(user);

        User retrievedUser = customUserService.getUserByToken(token);

        assertEquals(user, retrievedUser);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepositoryMock.findUserByUsername(username)).thenReturn(user);

        User retrievedUser = customUserService.getUserByUsername(username);

        assertEquals(user, retrievedUser);
    }
}
