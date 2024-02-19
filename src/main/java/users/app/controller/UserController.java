package users.app.controller;

import users.app.dto.UserCreateRequest;
import users.app.dto.UserUpdateRequest;
import users.app.service.UserService;
import users.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){

        User user = userService.findById(id);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id){

        userService.deleteUserById(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest){

        userService.save(userCreateRequest);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest userUpdateRequest){

        userService.updateUserById(id, userUpdateRequest);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUserPartially(@PathVariable("id") Long id, @RequestBody UserUpdateRequest userUpdateRequest){

        userService.updateUserPartiallyById(id, userUpdateRequest);
        return ResponseEntity.ok().build();

    }
}
