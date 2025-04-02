package com.example.sneaker.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.sneaker.entities.User;
import com.example.sneaker.repositories.UserRepository;
import com.example.sneaker.security.JwtService;
import com.example.sneaker.services.MyUserDetailsService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

	 private final AuthenticationManager authenticationManager;
	    private final JwtService jwtService;
	    private final MyUserDetailsService userDetailsService;
	    
	    
	  @Autowired
	  private UserRepository userRepository;
	  
	  
	  public UserController(AuthenticationManager authenticationManager, JwtService jwtService, MyUserDetailsService userDetailsService) {
	        this.authenticationManager = authenticationManager;
	        this.jwtService = jwtService;
	        this.userDetailsService = userDetailsService;
	    }
	  @GetMapping("/getAll")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	/*  @GetMapping("/{userId}")
	    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
	        Optional<User> user = userRepository.findById(userId);
	        if (user.isPresent()) {
	            return new ResponseEntity<>(user.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }*/
	  @GetMapping("/{username}")
	    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
	        Optional<User> user = userRepository.findByUsername(username);
	        if (user.isPresent()) {
	            return new ResponseEntity<>(user.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	  @PostMapping("/createUser") // Updated endpoint
	    public ResponseEntity<?> createUser(@RequestBody Map<String, String> requestBody) { // Changed to Map
	        try {
	            // Extract data from request body
	            String username = requestBody.get("username");
	            String email = requestBody.get("email");
	            String password = requestBody.get("password");
	            String fullName = requestBody.get("fullName");
	            String phoneNumber = requestBody.get("phoneNumber");

	            // Check for duplicate username or email
	            if (userRepository.existsByUsername(username)) {
	                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Username already exists."));
	            }
	            if (userRepository.existsByEmail(email)) {
	                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email already exists."));
	            }

	            // Hash the password
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            String hashedPassword = passwordEncoder.encode(password);

	            // Create a new User object
	            User user = new User();
	            user.setUserId(UUID.randomUUID()); // Generate UUID here
	            user.setUsername(username);
	            user.setEmail(email);
	            user.setPasswordHash(hashedPassword.getBytes(hashedPassword));
	            user.setSalt(passwordEncoder.encode(UUID.randomUUID().toString()).getBytes()); // Generate salt.
	            user.setFullName(fullName);
	            user.setPhoneNumber(phoneNumber);

	            // Set default values
	            user.setRegistrationDate(LocalDateTime.now());
	            user.setEnabled(true);
	            user.setEmailVerified(false);
	            user.setPhoneVerified(false);
	            user.setRole("USER");

	            // Save the user
	            User savedUser = userRepository.save(user);

	            // Return success response
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
	        }
	    }

	    @PutMapping("/updateUser/{userId}")
	    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User userDetails) {
	        Optional<User> user = userRepository.findById(userId);
	        if (user.isPresent()) {
	            User existingUser = user.get();
	            // Update fields as needed (excluding sensitive fields like passwordHash and salt)
	            existingUser.setUsername(userDetails.getUsername());
	            existingUser.setEmail(userDetails.getEmail());
	            existingUser.setFullName(userDetails.getFullName());
	            existingUser.setLastLogin(userDetails.getLastLogin());
	            existingUser.setEmailVerified(userDetails.isEmailVerified());
	            existingUser.setPhoneVerified(userDetails.isPhoneVerified());
	            existingUser.setEnabled(userDetails.isEnabled());
	            existingUser.setRole(userDetails.getRole());
	            existingUser.setProfilePictureUrl(userDetails.getProfilePictureUrl());
	            existingUser.setBio(userDetails.getBio());
	            existingUser.setPreferredCurrency(userDetails.getPreferredCurrency());
	            existingUser.setPreferredSizeUnits(userDetails.getPreferredSizeUnits());
	            existingUser.setPhoneNumber(userDetails.getPhoneNumber());
	            existingUser.setVerificationToken(userDetails.getVerificationToken());
	            existingUser.setResetPasswordToken(userDetails.getResetPasswordToken());
	            existingUser.setResetPasswordExpiry(userDetails.getResetPasswordExpiry());

	            User updatedUser = userRepository.save(existingUser);
	            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/disableUser/{userId}")
	    public ResponseEntity<User> deleteUser(@PathVariable UUID userId) {
	    	Optional<User> temp = userRepository.findById(userId);
	    	if (temp.isPresent()) {
	    		User existingUser = temp.get();
	    		existingUser.setEnabled(false);
	            User updatedUser = userRepository.save(existingUser);
	            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	    		
	    	}
	        else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody, HttpServletResponse response) {
	        String username = requestBody.get("username");
	        String password = requestBody.get("password");

	      
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	        );

	        if (authentication.isAuthenticated()) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            String token = jwtService.generateToken(userDetails);
	            
	            // Create HTTP-only cookie
	            Cookie jwtCookie = new Cookie("jwtToken", token);
	            jwtCookie.setHttpOnly(true);
	            jwtCookie.setPath("/"); // Set the cookie path
	            jwtCookie.setMaxAge((int) jwtService.getExpiration()); // Set the cookie expiry
	            // jwtCookie.setSecure(true); for production with HTTPS

	            response.addCookie(jwtCookie);

	            return ResponseEntity.ok(Map.of("message", "Login successful"));
	        } else {
	            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password."));
	        }
	    }
	    @PostMapping("/logout")
	    public ResponseEntity<?> logout(HttpServletResponse response) {
	        Cookie jwtCookie = new Cookie("jwtToken", null);
	        jwtCookie.setHttpOnly(true);
	        jwtCookie.setPath("/");
	        jwtCookie.setMaxAge(0); // Set max age to 0 to delete the cookie
	        // jwtCookie.setSecure(true); for production with HTTPS

	        response.addCookie(jwtCookie);
	        return ResponseEntity.ok(Map.of("message", "Logout successful"));
	    }
	    @GetMapping("/profile")
	    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("jwtToken".equals(cookie.getName())) {
	                    String token = cookie.getValue();
	                    if (jwtService.validateToken(token)) {
	                        String username = jwtService.extractUsername(token);
	                        Optional<User> user = userRepository.findByUsername(username);
	                        if (user.isPresent()) {
	                            return ResponseEntity.ok(user.get());
	                        } else {
	                            return ResponseEntity.notFound().build();
	                        }
	                    } else {
	                        return ResponseEntity.status(401).build(); // Invalid token
	                    }
	                }
	            }
	        }
	        return ResponseEntity.status(401).build(); // Cookie not found
	    }
    // ... other methods ...

}