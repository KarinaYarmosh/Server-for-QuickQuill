package users.app.service;

import users.app.dto.UserCreateRequest;
import users.app.dto.UserUpdateRequest;
import users.app.repository.UserRepository;
import users.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //post (create new user)
    public void save(UserCreateRequest userCreateRequest){
        User user = new User();

        user.setEmail(userCreateRequest.getEmail());
        user.setName(userCreateRequest.getName());
        user.setPassword(userCreateRequest.getPassword());

        userRepository.save(user);
    }

    //put (update)
    public void updateUserById(Long id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findUserById(id).orElseThrow();

        user.setEmail(userUpdateRequest.getEmail());
        user.setName(userUpdateRequest.getName());
        user.setPassword(userUpdateRequest.getPassword());

        userRepository.save(user);
    }

    //patch (update partially)
    public void updateUserPartiallyById(Long id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findUserById(id).orElseThrow();

        // Update only if the field in the request is not null
        if (userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getName() != null) {
            user.setName(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(userUpdateRequest.getPassword());
        }

        userRepository.save(user);
    }

    public void save(User user){
        userRepository.save(user);
    }

    //get (user by id)
    public User findById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }


    //get (all the users)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //delete
    public void deleteUserById(Long id){
        User user = userRepository.findUserById(id).orElseThrow();
        userRepository.delete(user);
    }
}
