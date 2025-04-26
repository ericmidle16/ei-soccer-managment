package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value="/add-product")
public class AdminAddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.getRequestDispatcher("WEB-INF/admin-add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String color = req.getParameter("color");
        String category = req.getParameter("category");
        req.setAttribute("name", name);
        req.setAttribute("price", price);
        req.setAttribute("description", description);
        req.setAttribute("category", category);
        req.setAttribute("color", color);

        Product product = new Product();
        boolean validationError = false;


        try {
            product.setName(name);
            req.setAttribute("nameError", false);
            req.setAttribute("nameMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("nameError", true);
            req.setAttribute("nameMessage", e.getMessage());
        }

        try {
            product.setPrice(price);
            req.setAttribute("priceError", false);
            req.setAttribute("priceMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("priceError", true);
            req.setAttribute("priceMessage", e.getMessage());
        }

        try {
            product.setDescription(description);
            req.setAttribute("descriptionError", false);
            req.setAttribute("descriptionMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("descriptionError", true);
            req.setAttribute("descriptionMessage", e.getMessage());
        }

        try {
            product.setCategoryId(category);
            req.setAttribute("categoryError", false);
            req.setAttribute("categoryMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("categoryError", true);
            req.setAttribute("categoryMessage", e.getMessage());
        }

        try {
            product.setColorId(color);
            req.setAttribute("colorError", false);
            req.setAttribute("colorMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("colorError", true);
            req.setAttribute("colorMessage", e.getMessage());
        }

        if(!validationError) {
            boolean productAdded = ProductDAO.add(product);
            req.setAttribute("productAdded", productAdded);
            if(productAdded) {
                req.setAttribute("productAddedMessage", "Successfully added a product!");
            } else {
                req.setAttribute("productAddedMessage", "Failed to add a product!");
            }
        }


        req.getRequestDispatcher("WEB-INF/admin-add-product.jsp").forward(req, resp);
    }

}
