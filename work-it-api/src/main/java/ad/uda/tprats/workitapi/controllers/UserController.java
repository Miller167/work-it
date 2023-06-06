package ad.uda.tprats.workit.workitapi.controllers;

import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workit.workitapi.helpers.CustomErrorException;

import ad.uda.tprats.workitdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        try {
            return userService.getUsers();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        try {
            return userService.getUserById(userId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        try {
            return userService.createUser(user);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(userId, user));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("User does not exist");
            }
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}
