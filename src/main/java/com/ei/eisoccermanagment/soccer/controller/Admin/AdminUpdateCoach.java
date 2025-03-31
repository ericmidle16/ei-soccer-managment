package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.User;
import com.ei.eisoccermanagment.soccer.model.Coach;
import com.ei.eisoccermanagment.soccer.model.CoachDAO;
import com.ei.eisoccermanagment.soccer.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/edit-coach")
public class AdminUpdateCoach extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        Coach coach = CoachDAO.get(Integer.parseInt(id));
        req.setAttribute("coach", coach);
        req.getRequestDispatcher("WEB-INF/admin-update-coach.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String id = req.getParameter("id"); // Vendor id from the hidden field
        req.setAttribute("id", id);
        String coachId = req.getParameter("coachId"); // Vendor id from the form
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String pronoun = req.getParameter("pronoun");
        String biography = req.getParameter("biography");
        String specialty = req.getParameter("specialty");

        boolean validationError = false;

        Coach coachOriginal = CoachDAO.get(Integer.parseInt(id)); // Get the coach from the data in the hidden field
        Coach coachNew = CoachDAO.get(Integer.parseInt(id));
        if (coachNew == null) {
            req.setAttribute("coachUpdated", false);
            req.setAttribute("coachUpdatedMessage", "Failed to update coach!");
        }


        try {
            coachNew.setFirstName(firstName);
            req.setAttribute("firstNameError", false);
            req.setAttribute("firstNameMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("firstNameError", true);
            req.setAttribute("firstNameMessage", e.getMessage());
        }

        try {
            coachNew.setLastName(lastName);
            req.setAttribute("lastNameError", false);
            req.setAttribute("lastNameMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("lastNameError", true);
            req.setAttribute("lastNameMessage", e.getMessage());
        }

        try {
            coachNew.setEmail(email);
            req.setAttribute("emailError", false);
            req.setAttribute("emailMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("emailError", true);
            req.setAttribute("emailMessage", e.getMessage());
        }

        try {
            coachNew.setAge(Integer.parseInt(age));
            req.setAttribute("ageError", false);
            req.setAttribute("ageMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("ageError", true);
            req.setAttribute("ageMessage", e.getMessage());
        }

        try {
            coachNew.setPronoun(pronoun);
            req.setAttribute("pronounError", false);
            req.setAttribute("pronounMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("pronounError", true);
            req.setAttribute("pronounMessage", e.getMessage());
        }

        try {
            coachNew.setBiography(biography);
            req.setAttribute("biographyError", false);
            req.setAttribute("biographyMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("biographyError", true);
            req.setAttribute("biographyMessage", e.getMessage());
        }

        try {
            coachNew.setSpecialty(specialty);
            req.setAttribute("specialtyError", false);
            req.setAttribute("specialtyMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("specialtyError", true);
            req.setAttribute("specialtyMessage", e.getMessage());
        }

        req.setAttribute("coach", coachNew);

        if(!validationError) {
            boolean coachUpdated = CoachDAO.update(coachOriginal, coachNew);
            req.setAttribute("coachUpdated", coachUpdated);
            if(coachUpdated) {
                req.setAttribute("coachUpdatedMessage", "Successfully updated coach!");
            } else {
                req.setAttribute("coachUpdatedMessage", "Failed to update coach!");
            }
        }


        req.getRequestDispatcher("WEB-INF/admin-update-coach.jsp").forward(req, resp);

    }

}
