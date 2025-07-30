package com.meta.smartbiz.controller;

import com.meta.smartbiz.entity.User;
import com.meta.smartbiz.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // --------------------- Dashboard & Pages ---------------------

    @GetMapping({"/", "/index"})
    public String dashboard() {
        return "redirect:/dashboard";
    }

    @GetMapping("/sales")
    public String sales() {
        return "sales";
    }

    @GetMapping("/expenses")
    public String expenses() {
        return "expenses";
    }

    @GetMapping("/parties")
    public String parties() {
        return "parties";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    // --------------------- Register ---------------------

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String role,
            @RequestParam String password,
            @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return "register";
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "redirect:/login";
    }

    // --------------------- Login ---------------------

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password) {

        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/index"; // Login success
        } else {
            return "login"; // Login fail
        }
    }
}