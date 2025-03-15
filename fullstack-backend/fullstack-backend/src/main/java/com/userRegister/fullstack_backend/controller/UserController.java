package com.userRegister.fullstack_backend.controller;

import com.userRegister.fullstack_backend.exception.UserNotFoundException;
import com.userRegister.fullstack_backend.model.User;
import com.userRegister.fullstack_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3001")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    User mewUser(@RequestBody User newUser){
       return userRepo.save(newUser);
    }

    @GetMapping("/user")
    List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepo.findById(id)
                .map(user ->{
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "User with id "+id+" successfully deleted";
    }

}
