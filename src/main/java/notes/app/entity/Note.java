package notes.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import users.app.entity.User;

import java.util.List;

@Entity
@Table(name = "notes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "context")
    private String context;

    @Column(name = "is_liked")
    private Boolean like;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
