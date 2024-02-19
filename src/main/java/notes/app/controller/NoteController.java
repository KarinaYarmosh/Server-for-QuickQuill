package notes.app.controller;


import notes.app.dto.NoteCreateRequest;
import notes.app.dto.NoteUpdateRequest;
import notes.app.entity.Note;
import notes.app.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.app.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    //USER

    //get all notes by user id
    @GetMapping("/{userId}")
    public ResponseEntity<List<Note>> getAllNotesByUser(@PathVariable Long userId) {

        //find if user is exist???

        List<Note> notes = noteService.findAllNotesByUserId(userId);

        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notes);
        }

    }

    //get one note by id and by user id (it's ok structure?)
    @GetMapping("/{userId}/{noteId}")
    public ResponseEntity<Note> getNoteByIdAndUser(@PathVariable Long userId, @PathVariable Long noteId) {

        //find if user is exist???

        Note note = noteService.findNoteByIdAndUserId(noteId, userId);

        if (note != null) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //delete one note by note id and user id
    @DeleteMapping("/{userId}/{noteId}")
    public ResponseEntity<Void> deleteNoteByIdAndUser(@PathVariable Long userId, @PathVariable Long noteId) {

        //find if user is exist???

        Note note = noteService.findNoteByIdAndUserId(noteId, userId);

        if (note != null) {
            noteService.deleteById(noteId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //??
//    //create note for user of user id (post)
//    @PostMapping("/{userId}")
//    public ResponseEntity<Void> createNoteForUser(@PathVariable Long userId,
//                                                  @RequestBody NoteCreateRequest noteCreateRequest) {
//
//        User user = userService.findById(userId);
//
//    }



    //ADMIN

    //get all notes
    @GetMapping("")
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }

    //get note by note id
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id){
        Note note = noteService.findById(id);
        return ResponseEntity.ok(note);
    }

    //delete note by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable("id") Long id){
        Note note = noteService.findById(id);
        return ResponseEntity.ok(note);
    }

    //create note (post)
    @PostMapping("")
    public ResponseEntity<Void> createNote(@RequestBody NoteCreateRequest noteCreateRequest){
        noteService.save(noteCreateRequest);
        return ResponseEntity.ok().build();
    }

    //edit note (put)
    @PutMapping("/{id}")
    public ResponseEntity<Void> editNoteById(@PathVariable Long id,
                                             @RequestBody NoteUpdateRequest noteUpdateRequest){
        noteService.updateNoteById(id, noteUpdateRequest);
        return ResponseEntity.ok().build();
    }

    //edit note partially (patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyEditNoteById(@PathVariable Long id,
                                                      @RequestBody NoteUpdateRequest noteUpdateRequest){
        noteService.updateNotePartiallyById(id, noteUpdateRequest);
        return ResponseEntity.ok().build();
    }


    //GET, POST, PUT, PATCH, DELETE note/notes including user id



}
