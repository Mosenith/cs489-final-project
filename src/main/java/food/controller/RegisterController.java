package food.controller;

import food.domain.RegistrationForm;
import food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("RegistrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));

        return "redirect:/api/auth/login";
    }
}
