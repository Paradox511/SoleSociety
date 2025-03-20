package com.example.sneaker.api;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) {
        // Retrieve user data from database
        User user = new User();
        user.setUserId(userId);
        user.setUsername("testUser");
        return user;
    }

    // ... other methods ...

    //User Class
    static class User{
        private UUID userId;
        private String username;

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}