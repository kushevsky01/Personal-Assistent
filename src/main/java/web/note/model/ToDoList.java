package web.note.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_todolist")
public class ToDoList{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String createTime;
    private String updateTime;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "toDoList", orphanRemoval = true)
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Affairs> affairses = new ArrayList<>();
}
