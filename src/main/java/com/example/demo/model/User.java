package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;

@Component //used to generate this class as a Bean
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "revature_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userEmail;
    private String userPassword;
    /* private List<Item> userCart; */
}
