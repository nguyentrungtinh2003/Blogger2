package com.TrungTinhFullStack.Blogger2.Service;

import com.TrungTinhFullStack.blog_backend_http.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    User register(User user);
    void createInitialAdmin();
    String hashPassword(String password);
    User findById(Long userId);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User newUser);
    void deleteUser(Long id);
}
