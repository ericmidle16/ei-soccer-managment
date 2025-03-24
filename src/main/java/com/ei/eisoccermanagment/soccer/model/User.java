package com.ei.eisoccermanagment.soccer.model;

import com.ei.eisoccermanagment.shared.Validators;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class User implements Comparable<User> {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private char[] password;
    private String language;
    private String status;
    private String privileges;
    private Instant createdAt;
    private String timezone;
    private String pronoun;
    private String biography;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        if (!Validators.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.equals("") && !Validators.isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phone = phone;
    }

    public char[] getPassword() {
        return password;
    }


    public void setPassword(char[] password) {
        if (password != null) {
            String passwordStr = String.valueOf(password);
            if (!Validators.isStrongPassword(passwordStr)) {
                throw new IllegalArgumentException("Password requires 8 characters with at least 3 out 4 (uppercase letter, lowercase letter, number, special character)");
            }
        }
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (!Validators.isValidLanguage(language)) {
            throw new IllegalArgumentException("Invalid language");
        }
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public Date getCreatedAtDate() {
        return Date.from(createdAt);
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(int userId, String firstName, String lastName, String email, String phone, char[] password, String language, String status, String privileges, Instant createdAt, String timezone, String pronoun, String biography) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.language = language;
        this.status = status;
        this.privileges = privileges;
        this.createdAt = createdAt;
        this.timezone = timezone;
        this.pronoun = pronoun;
        this.biography = biography;
    }

    @Override
    public int compareTo(@NotNull User o) {
        int result = this.lastName.compareTo(o.lastName);
        if(result == 0) {
            result = this.firstName.compareTo(o.firstName);
        }
        return result;
    }
}
