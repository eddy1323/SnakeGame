package lt.codeacademy.snakeGame.repository;

import lt.codeacademy.snakeGame.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
