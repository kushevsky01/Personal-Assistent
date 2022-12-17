package web.note.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String secondPassword;

    private String active;
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ToDoList> toDoLists = new ArrayList<>();

    public User(String name, String username, String password, String secondPassword, String active, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.secondPassword=secondPassword;
        this.active = active;
        this.role = role;

    }
}
