package lt.codeacademy.snakeGame.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.snakeGame.repository.UserRepository;
import lt.codeacademy.snakeGame.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    public UserController(UserRepository userRepository) {
    }
    @GetMapping("/user")
    public String userPage(Model model, HttpSession session) {
        if (session.getAttribute("authenticated") != null) {
            String username = (String) session.getAttribute("username");
            User user = new User();
            user.setUsername(username);
            model.addAttribute("user", user);
            return "user";
        } else {
            return "redirect:/login";
        }
    }

    /*@GetMapping(path = "/user")
    public String userPage(Model model, UserRepository userRepository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            User user = userRepository.findByUsername(username);
            model.addAttribute("user", user);
            return "user";
        } else {
            // Handle the case where authentication.getPrincipal() is not a UserDetails
            // You might want to redirect to an error page or handle this case as appropriate.
            return "error";
        }
    }*/
}
