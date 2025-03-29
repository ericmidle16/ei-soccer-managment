package com.ei.eisoccermanagment.soccer.model;

import com.ei.eisoccermanagment.shared.Validators;
import org.jetbrains.annotations.NotNull;

public class Coach implements Comparable<Coach> {
    private int coachId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String pronoun;
    private String biography;
    private String specialty;

    public Coach() {
    }

    public Coach(int coachId, String firstName, String lastName, String email, int age, String pronoun, String biography, String specialty) {
        this.coachId = coachId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.pronoun = pronoun;
        this.biography = biography;
        this.specialty = specialty;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public int compareTo(@NotNull Coach o) {
        int result = this.lastName.compareTo(o.lastName);
        if(result == 0) {
            result = this.firstName.compareTo(o.firstName);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachId=" + coachId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", pronoun='" + pronoun + '\'' +
                ", biography='" + biography + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
