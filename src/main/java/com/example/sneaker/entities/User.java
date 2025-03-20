package com.example.sneaker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Lob
    @Column(nullable = false)
    private byte[] passwordHash;

    @Lob
    @Column(nullable = false)
    private byte[] salt;

    private String fullName;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLogin;
    private boolean emailVerified;
    private boolean phoneVerified;
    private boolean enabled;
    private String role;
    private String profilePictureUrl;
    private String bio;
    private String preferredCurrency;
    private String preferredSizeUnits;
    private String phoneNumber;
    private String verificationToken;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordExpiry;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favorite> favorites;

    // Constructors (default and parameterized), getters, and setters

    public User() {
    }
    
    

	// ...getters and setters...

    
    public Set<Favorite> getFavorites() {
        return favorites;
    }

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPreferredCurrency() {
		return preferredCurrency;
	}

	public void setPreferredCurrency(String preferredCurrency) {
		this.preferredCurrency = preferredCurrency;
	}

	public String getPreferredSizeUnits() {
		return preferredSizeUnits;
	}

	public void setPreferredSizeUnits(String preferredSizeUnits) {
		this.preferredSizeUnits = preferredSizeUnits;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public LocalDateTime getResetPasswordExpiry() {
		return resetPasswordExpiry;
	}

	public void setResetPasswordExpiry(LocalDateTime resetPasswordExpiry) {
		this.resetPasswordExpiry = resetPasswordExpiry;
	}

	public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }
}

