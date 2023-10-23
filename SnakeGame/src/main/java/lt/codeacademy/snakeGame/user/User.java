package lt.codeacademy.snakeGame.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (schema = "user_info" ,name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false, unique=true)
    private String username;
    @Column(nullable=false)
    private String password;


    /*@OneToOne(mappedBy = "user")
    private UserScore userScore;*/
}
