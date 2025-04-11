package com.ei.eisoccermanagment.soccer.controller;

import com.ei.eisoccermanagment.soccer.model.User;
import com.ei.eisoccermanagment.soccer.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");
        if(user == null) {
            session.setAttribute("flashMessageWarning", "You must be logged in to edit your profile.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=edit-profile"));
            return;
        } else if(user != null && !user.getStatus().equals("active")) {
            session.setAttribute("flashMessageDanger", "Your account is locked or inactive.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            return;
        }

        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String language = req.getParameter("language");
        String pronoun = req.getParameter("pronoun");
        String biography = req.getParameter("biography");
        String timeZone = req.getParameter("timezones");
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser"); // Do I need to make a hard copy of the user?
        boolean errorFound = false;
        if(firstName != null && !firstName.equals(user.getFirstName())) {
            user.setFirstName(firstName);
        }
        if(lastName != null && !lastName.equals(user.getLastName())) {
            user.setLastName(lastName);
        }
        String originalEmail = user.getEmail();
        if(email != null && !email.equals("") && !email.equals(user.getEmail()) && UserDAO.get(email) != null) {
            errorFound = true;
            req.setAttribute("emailError", "A user with that email already exists.");
        } else {
            // The user entered an email address that doesn't exist.
            try {
                user.setEmail(email);
            } catch (IllegalArgumentException e) {
                errorFound = true;
                req.setAttribute("emailError", e.getMessage());
            }
        }


        try {
            if(phone != null && !phone.equals(user.getPhone())) {
                user.setPhone(phone);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("phoneError", e.getMessage());
        }

        try {
            if(!language.equals(user.getLanguage())) {
                user.setLanguage(language);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("languageError", e.getMessage());
        }

        try {
            if(!timeZone.equals(user.getTimezone())) {
                user.setTimezone(timeZone);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("timezoneError", e.getMessage());
        }

        try {
            if(!pronoun.equals(user.getPronoun())) {
                user.setPronoun(pronoun);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("pronounError", e.getMessage());
        }

        try {
            if(!biography.equals(user.getBiography())) {
                user.setBiography(biography);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("biographyError", e.getMessage());
        }

        if(!errorFound) {
            boolean userUpdated = false;
            try {
                userUpdated = UserDAO.update(originalEmail, user);
            } catch(RuntimeException e) {
                session.setAttribute("flashMessageDanger", e.getMessage()); // Change to a message like "Your profile was not updated"
            }
            if(userUpdated) {
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", "Your profile was updated");
            }
        }

        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }

    // populate dropdown function
    public List<String> populateUSTimezones() {
        List<String> timezones = Arrays.asList(java.util.TimeZone.getAvailableIDs());
        List<String> UStimezones = new ArrayList<>();
        for(String timezone : timezones) {
            if(timezone.startsWith("America/")){
                UStimezones.add(timezone);
            }
        }
        return UStimezones;
    }
}
