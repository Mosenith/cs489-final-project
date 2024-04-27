package food.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import food.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import food.domain.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomUserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserService customUserService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Create a test user before each test
        testUser = new User("test_user", "password");
        testUser.setFullName("Test User");
        testUser.setCity("Test City");
        // You can set other properties as needed
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        // Delete the test user after each test
        userRepository.delete(testUser);
    }

    @Test
    public void testSaveAndGetToken() {
        // Test saveToken and getTokenByUsername methods
        String token = "test_token";
        customUserService.saveToken(testUser.getUsername(), token);
        assertEquals(token, customUserService.getTokenByUsername(testUser.getUsername()));
    }

    @Test
    public void testGetUserByToken() {
        // Test getUserByToken method
        String token = "test_token";
        testUser.setToken(token);
        userRepository.save(testUser);
        assertEquals(testUser, customUserService.getUserByToken(token));
    }

    @Test
    public void testGetUserByUsername() {
        // Test getUserByUsername method
        assertEquals(testUser, customUserService.getUserByUsername(testUser.getUsername()));
    }
}
