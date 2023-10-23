package lt.codeacademy.snakeGame.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    private String email;
    private String username;
    private String password;
}
