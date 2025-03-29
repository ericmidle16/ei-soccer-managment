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
import java.util.List;

@WebServlet("/coaches")
public class AdminCoaches extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            session.setAttribute("flashMessageWarning", "You do not have permission to access this page");
            resp.sendRedirect("login");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<Coach> coaches = CoachDAO.getAll();
        req.setAttribute("coaches", coaches);
        req.setAttribute("pageTitle", "All Coaches");
        req.getRequestDispatcher("WEB-INF/admin-coaches.jsp").forward(req, resp);
    }
}
