package lt.codeacademy.snakeGame.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_score", schema = "user_info")
public class UserScore {
    @Id
    private String username;
    LocalDateTime scoreTime = LocalDateTime.now();
    private int score;

    /*@OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;*/
}
