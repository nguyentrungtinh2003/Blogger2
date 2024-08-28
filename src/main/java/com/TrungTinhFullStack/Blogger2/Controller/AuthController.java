package com.TrungTinhFullStack.Blogger2.Controller;

import com.TrungTinhFullStack.blog_backend_http.Entity.User;
import com.TrungTinhFullStack.blog_backend_http.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

   

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("Registration successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
           return  userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user) {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete User Success");
    }
}
