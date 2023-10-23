package lt.codeacademy.snakeGame.services;

import lt.codeacademy.snakeGame.myExceptions.LoginException;
import lt.codeacademy.snakeGame.myExceptions.RegistrationException;
import lt.codeacademy.snakeGame.repository.UserRepository;
import lt.codeacademy.snakeGame.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(String email, String username, String password) throws RegistrationException {
        User existingUserByEmail = userRepository.findByEmail(email);
        User existingUserByUsername = userRepository.findByUsername(username);

        if (!isPassValid(password)) {
            throw new RegistrationException("registration.error.password");
        }
        if (existingUserByEmail != null) {
            throw new RegistrationException("registration.error.email.duplicate");
        }

        if (existingUserByUsername != null) {
            throw new RegistrationException("registration.error.username.duplicate");
        }

       User user = new User();
        user.setEmail(email);
       user.setUsername(username);
       user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return user;
    }

    public User loginUser(String username, String password) throws LoginException {
        User existingUser = getUserByUsername(username);

        if (existingUser != null) {
            if (passwordMatches(existingUser, password)) {
                return existingUser;
            } else {
                throw new LoginException("login.error");
            }
        } else {
            throw new LoginException("login.not.found");
        }
    }
    private static boolean isPassValid(String password) {
        if (password.contains(" ")) {
            return false;
        }

        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&(){}:;',?/*~$^+=<>]).{8,20}$");
    }
    private boolean passwordMatches(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
