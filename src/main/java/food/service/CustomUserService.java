package food.service;

import food.domain.User;
import food.repository.UserRepository;
import food.service.dto.UserAdapter;
import food.service.dto.UserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
//    @Autowired
    private UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        System.out.println("User is valid===");
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        if(user.getRole().equalsIgnoreCase("admin")) {
            System.out.println("Role == Admin");
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            System.out.println("Role == User");
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    // Method to save token associated with user
    public void saveToken(String username, String token) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            user.setToken(token);
            userRepository.save(user);
        }
    }

    // Method to retrieve token associated with user
    public String getTokenByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null ? user.getToken() : null;
    }

    public User getUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    // add some user data
    public void addUserData() {
        User user1 = new User(null, "miu1", "root1", "Chris Alven", "1000N 4th",
                "Fairfield", "Iowa", "52557", "111-2222");
        user1.setRole("USER");

        User user2 = new User(null, "miu2", "root2", "Simon Love", "1222N 3th",
                "Iowa City", "Iowa", "52227", "999-7777");
        user2.setRole("ADMIN");


        userRepository.save(user1);
        userRepository.save(user2);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = new ArrayList<>();

        for(User usr : userRepository.findAll()) {
            userDtos.add(UserAdapter.getUserDtoFromUser(usr));
        }

        return userDtos;
    }
}
