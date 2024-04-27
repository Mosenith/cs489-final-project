package food.controller;

import food.config.jwt.AuthenticationRequest;
import food.config.jwt.AuthenticationResponse;
import food.config.jwt.JwtTokenProvider;
import food.domain.User;
import food.service.CustomUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserService userService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retrieve additional user information from the database
        User user = userService.getUserByUsername(authenticationRequest.getUsername());

        String jwtToken = jwtTokenProvider.createToken(authentication);
        userService.saveToken(authenticationRequest.getUsername(), jwtToken);

        System.out.println("Post Token ==> " + jwtToken);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @GetMapping("/api/auth/login") // Mapping to serve login.html
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // Set the name of your Thymeleaf template (login.html)
        return modelAndView;
    }
}
