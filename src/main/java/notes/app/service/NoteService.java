package notes.app.service;

import notes.app.dto.NoteCreateRequest;
import notes.app.dto.NoteUpdateRequest;
import notes.app.entity.Note;
import notes.app.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    //autowired is used to inject the NoteRepository dependency
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    //USER

    //get all notes by user id
    public List<Note> findAllNotesByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    //get one note by id by user id
    public Note findNoteByIdAndUserId(Long noteId, Long userId) {
        return noteRepository.findByIdAndUserId(noteId, userId);
    }

    //post (create new note)


    //ADMIN

    //get all notes
    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    //get note by note id
    public Note findById(Long id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found" + id));
    }

    //delete note by id
    public void deleteById(Long id){
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
        noteRepository.deleteById(id);
    }

    //post (create new note)
    public void save(Note note){
        noteRepository.save(note);
    }

    //post (create new note)
    public void save(NoteCreateRequest noteCreateRequest){
        Note note = new Note();

        note.setCategory(noteCreateRequest.getCategory());
        note.setLike(noteCreateRequest.getLike());
        note.setContext(noteCreateRequest.getContent());

        noteRepository.save(note);
    }

    //put (update)
    public void updateNoteById(Long id, NoteUpdateRequest noteUpdateRequest){
        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Note not found"));

        note.setCategory(noteUpdateRequest.getCategory());
        note.setLike(noteUpdateRequest.getLike());
        note.setContext(noteUpdateRequest.getContent());

        noteRepository.save(note);
    }

    //patch (update partially)
    public void updateNotePartiallyById(Long id, NoteUpdateRequest noteUpdateRequest){
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found"));

        // Update only if the field in the request is not null
        if (noteUpdateRequest.getCategory() != null) {
            note.setCategory(noteUpdateRequest.getCategory());
        }
        if (noteUpdateRequest.getLike() != null) {
            note.setLike(noteUpdateRequest.getLike());
        }
        if (noteUpdateRequest.getContent() != null) {
            note.setContext(noteUpdateRequest.getContent());
        }

        noteRepository.save(note);
    }

    //    //get notes by category
    //    public Note findByCategory(String category) {
    //        return noteRepository.findNoteByCategory(category)
    //                .orElseThrow(() -> new NoSuchElementException("Note not found"));
    //    }
    //
    //    //get all notes by like
    //    public List<Note> findAllNotesByLike(Boolean like){
    //        return noteRepository.findNoteByLike(like)
    //                .orElseThrow(() -> new NoSuchElementException("Note not found"));
    //    }

}
