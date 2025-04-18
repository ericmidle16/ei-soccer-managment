package com.ei.eisoccermanagment.soccer.controller.Admin;

import com.ei.eisoccermanagment.soccer.model.User;
import com.ei.eisoccermanagment.soccer.model.UserDAO;
import com.ei.eisoccermanagment.soccer.model.Product;
import com.ei.eisoccermanagment.soccer.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/edit-product")
public class AdminUpdateProduct extends HttpServlet {
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
        Product product = ProductDAO.get(Integer.parseInt(id));
        req.setAttribute("product", product);
        req.getRequestDispatcher("WEB-INF/admin-update-product.jsp").forward(req, resp);
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
        String productId = req.getParameter("productId"); // Vendor id from the form
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String color = req.getParameter("color");

        boolean validationError = false;

        Product productOriginal = ProductDAO.get(Integer.parseInt(id)); // Get the coach from the data in the hidden field
        Product productNew = ProductDAO.get(Integer.parseInt(id));
        if (productNew == null) {
            req.setAttribute("productUpdated", false);
            req.setAttribute("productUpdatedMessage", "Failed to update a product!");
        }

        try {
            productNew.setName(name);
            req.setAttribute("nameError", false);
            req.setAttribute("nameMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("nameError", true);
            req.setAttribute("nameeMessage", e.getMessage());
        }

        try {
            productNew.setPrice(Double.parseDouble(price));
            req.setAttribute("priceError", false);
            req.setAttribute("priceMessage", "Looks good!");
        } catch(IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("priceError", true);
            req.setAttribute("priceMessage", e.getMessage());
        }

        try {
            productNew.setDescription(description);
            req.setAttribute("descriptionError", false);
            req.setAttribute("descriptionMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("descriptionError", true);
            req.setAttribute("descriptionMessage", e.getMessage());
        }

//        try {
//            productNew.setColor(color);
//            req.setAttribute("colorError", false);
//            req.setAttribute("colorMessage", "Looks good!");
//        } catch (IllegalArgumentException e) {
//            validationError = true;
//            req.setAttribute("colorError", true);
//            req.setAttribute("colorMessage", e.getMessage());
//        }

        req.setAttribute("product", productNew);

        if(!validationError) {
            boolean productUpdated = ProductDAO.update(productOriginal, productNew);
            req.setAttribute("productUpdated", productUpdated);
            if(productUpdated) {
                req.setAttribute("productUpdatedMessage", "Successfully updated a product!");
            } else {
                req.setAttribute("productUpdatedMessage", "Failed to update a product !");
            }
        }


        req.getRequestDispatcher("WEB-INF/admin-update-product.jsp").forward(req, resp);

    }
}
