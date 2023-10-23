package lt.codeacademy.snakeGame.controller;

import lt.codeacademy.snakeGame.myExceptions.RegistrationException;
import lt.codeacademy.snakeGame.repository.UserRepository;
import lt.codeacademy.snakeGame.services.UserService;
import lt.codeacademy.snakeGame.user.User;
import lt.codeacademy.snakeGame.user.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(@ModelAttribute UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new UserRegistrationForm());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationForm registrationForm, Model model) {
        String email = registrationForm.getEmail();
        String username = registrationForm.getUsername();
        String password = registrationForm.getPassword();

        try {
            User registeredUser = userService.registerUser(email, username, password);

            if (registeredUser != null) {
                model.addAttribute("successMessage", "registration.success");
            } else {
                model.addAttribute("errorMessage", "An error occurred during registration.");

            }
        } catch (RegistrationException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("registrationForm", registrationForm);
        return "register";
    }

}

