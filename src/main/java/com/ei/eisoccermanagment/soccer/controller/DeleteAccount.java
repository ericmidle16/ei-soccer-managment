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

@WebServlet("/delete-account")
public class DeleteAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");
        // This is to redirect them in case they are not signed in or have an inactive account
        if(user == null) {
            session.setAttribute("flashMessageWarning", "You must be logged in to delete your account.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=delete-account"));
            return;
        } else if(user != null && !user.getStatus().equals("active")) {
            session.setAttribute("flashMessageDanger", "Your account is locked or inactive.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            return;
        }
        req.setAttribute("pageTitle", "Delete Account");
        req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setAttribute("email", email);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");

        boolean errorFound = false;
        User userFromDatabase = UserDAO.get(email);
        if(userFromDatabase == null) {
            errorFound = true;
            session.setAttribute("flashMessageWarning", "You entered the wrong email.");
        }
        if(!errorFound) {
            boolean deleted = UserDAO.delete(user);
            if(deleted) {
                // Invalidate the session data after deletion
                session.invalidate();
                // Creating a new session
                session = req.getSession();
                session.setAttribute("flashMessageWarning", "Your account has been deleted.");
            }
        }

        req.setAttribute("pageTitle", "Delete Account");
        req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
    }
}
