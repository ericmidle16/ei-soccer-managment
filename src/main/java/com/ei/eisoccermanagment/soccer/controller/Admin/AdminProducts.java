package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class AdminProducts extends HttpServlet {
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
        List<Product> products = ProductDAO.getAll();
        req.setAttribute("products", products);
        req.setAttribute("pageTitle", "All Products");
        req.getRequestDispatcher("WEB-INF/admin-products.jsp").forward(req, resp);
    }
}
