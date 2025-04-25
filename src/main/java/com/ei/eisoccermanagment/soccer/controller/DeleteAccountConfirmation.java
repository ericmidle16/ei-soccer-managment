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

@WebServlet("/delete-account-confirm")
public class DeleteAccountConfirmation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user != null) {
            boolean deleted = UserDAO.delete(user);
            if (deleted) {
                session.invalidate();
                session = req.getSession();
                session.setAttribute("flashMessageWarning", "Your account has been deleted.");
            } else {
                session.setAttribute("flashMessageDanger", "There was a problem deleting your account.");
            }
        }

        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }

}
