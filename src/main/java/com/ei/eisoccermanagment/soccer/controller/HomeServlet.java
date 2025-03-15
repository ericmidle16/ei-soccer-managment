package com.ei.eisoccermanagment.soccer.controller;

import com.ei.eisoccermanagment.soccer.model.Review;
import com.ei.eisoccermanagment.soccer.model.ReviewDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Review> reviews = ReviewDAO.getAll();
        req.setAttribute("reviews", reviews);
        req.setAttribute("pageTitle", "Home");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}

