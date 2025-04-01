package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.User;
import com.ei.eisoccermanagment.soccer.model.CoachDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-coach")
public class AdminDeleteCoach extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String id = req.getParameter("id");
        CoachDAO.delete(id);

        resp.sendRedirect("WEB-INF/admin-coaches.jsp");
    }
}
