import lt.codeacademy.snakeGame.repository.UserRepository;
import lt.codeacademy.snakeGame.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.security.spec.InvalidKeySpecException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRepositoryTest {
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Rollback(false)
    public class UserRepositoryTests {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired(required = false)
        private UserRepository repo;

        @Test
        public void testCreateUser() throws InvalidKeySpecException {
            User user = new User();
            user.setEmail("jonas1@gmail.com");
            user.setUsername("Jonas69");
            user.setPassword("Pass123!");

            User savedUser = repo.save(user);

            User existUser = entityManager.find(User.class, savedUser.getId());

            assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
        }
    }

}
