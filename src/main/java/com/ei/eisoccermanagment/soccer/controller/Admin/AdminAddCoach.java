package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.Coach;
import com.ei.eisoccermanagment.soccer.model.CoachDAO;
import com.ei.eisoccermanagment.soccer.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value="/add-coach")
public class AdminAddCoach extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.getRequestDispatcher("WEB-INF/admin-add-coach.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String pronoun = req.getParameter("pronoun");
        String biography = req.getParameter("biography");
        String specialty = req.getParameter("specialty");
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("email", email);
        req.setAttribute("age", age);
        req.setAttribute("pronoun", pronoun);
        req.setAttribute("biography", biography);
        req.setAttribute("specialty", specialty);

        Coach coach = new Coach();
        boolean validationError = false;


        try {
            coach.setFirstName(firstName);
            req.setAttribute("firstNameError", false);
            req.setAttribute("firstNameMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("firstNameError", true);
            req.setAttribute("firstNameMessage", e.getMessage());
        }

        try {
            coach.setLastName(lastName);
            req.setAttribute("lastNameError", false);
            req.setAttribute("lastNameMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("lastNameError", true);
            req.setAttribute("lastNameMessage", e.getMessage());
        }

        try {
            coach.setEmail(email);
            req.setAttribute("emailError", false);
            req.setAttribute("emailMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("emailError", true);
            req.setAttribute("emailMessage", e.getMessage());
        }

        try {
            coach.setAge(age);
            req.setAttribute("ageError", false);
            req.setAttribute("ageMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("ageError", true);
            req.setAttribute("ageMessage", e.getMessage());
        }

        try {
            coach.setPronoun(pronoun);
            req.setAttribute("pronounError", false);
            req.setAttribute("pronounMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("pronounError", true);
            req.setAttribute("pronounMessage", e.getMessage());
        }

        try {
            coach.setBiography(biography);
            req.setAttribute("biographyError", false);
            req.setAttribute("biographyMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("biographyError", true);
            req.setAttribute("biographyMessage", e.getMessage());
        }

        try {
            coach.setSpecialty(specialty);
            req.setAttribute("specialtyError", false);
            req.setAttribute("specialtyMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("specialtyError", true);
            req.setAttribute("specialtyMessage", e.getMessage());
        }


        if(!validationError) {
            boolean coachAdded = CoachDAO.add(coach);
            req.setAttribute("coachAdded", coachAdded);
            if(coachAdded) {
                req.setAttribute("coachAddedMessage", "Successfully added coach!");
            } else {
                req.setAttribute("coachAddedMessage", "Failed to add coach!");
            }
        }


        req.getRequestDispatcher("WEB-INF/admin-add-coach.jsp").forward(req, resp);
    }

}
