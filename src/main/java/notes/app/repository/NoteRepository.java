package notes.app.repository;

import notes.app.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import users.app.entity.User;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    //one note by id
    Optional<Note> findById(Long id);

    //all notes by user id
    List<Note> findByUserId(Long userId);

    //find note by id and by user id
    Note findByIdAndUserId(Long id, Long userId);

    //one note by user id
    Optional<Note> findNoteByUserId(Long userId);

    //all notes by like
    @Query("SELECT n FROM Note n WHERE n.like = :like")
    Optional<User> findNoteByLike(@Param("like") Boolean like);

    //all notes by category
    @Query("SELECT n FROM Note n WHERE n.category = :category")
    Optional<User> findNoteByCategory(@Param("category") String category);
}
