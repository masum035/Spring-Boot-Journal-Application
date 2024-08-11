package com.abdullah_al_masum.journalAppByMasum.Controller;

import com.abdullah_al_masum.journalAppByMasum.Entity.User;
import com.abdullah_al_masum.journalAppByMasum.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller --> Service --> Repository
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if(allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        try {
            User userObj = userService.create(user);
            return new ResponseEntity<>(userObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId user_id) {
        Optional<User> userObjById = userService.getUserById(user_id);
        if (userObjById.isPresent()) {
            return new ResponseEntity<>(userObjById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId user_id) {
        userService.deleteUserById(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{username}")
    public ResponseEntity<?> updateUserById(@RequestBody User modifiedUser, @PathVariable String username) {
        User userInDB = userService.findByUserName(username);
        if (userInDB != null) {
            userInDB.setUserName(!modifiedUser.getUserName().isEmpty() ? modifiedUser.getUserName() : userInDB.getUserName());
            userInDB.setUserPassword(!modifiedUser.getUserPassword().isEmpty() ? modifiedUser.getUserPassword() : userInDB.getUserPassword());
            userService.create(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
