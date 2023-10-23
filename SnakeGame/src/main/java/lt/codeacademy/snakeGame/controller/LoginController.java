package lt.codeacademy.snakeGame.controller;

import jakarta.servlet.http.HttpSession;
import lt.codeacademy.snakeGame.repository.UserRepository;
import lt.codeacademy.snakeGame.services.UserService;
import lt.codeacademy.snakeGame.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(@ModelAttribute UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /*@PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            userService.loginUser(username, password);
            return "redirect:/user";
        } catch (LoginException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "login";
        }
    }*/
    @PostMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String storedPasswordHash = user.getPassword();
            if (passwordEncoder.matches(password, storedPasswordHash)) {
                session.setAttribute("authenticated", true);
                session.setAttribute("username", username);
                modelAndView.setViewName("redirect:/user");
            } else {
                modelAndView.setViewName("login");
                modelAndView.addObject("error", "Invalid username or password.");
            }
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "User not found.");
        }

        return modelAndView;
    }
}
