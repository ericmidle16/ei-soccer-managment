package com.ei.eisoccermanagment.soccer.controller;

import com.ei.eisoccermanagment.soccer.model.Product;
import com.ei.eisoccermanagment.soccer.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("prod_id");
        String quantityStr = req.getParameter("qty");

        boolean errorFound = false;
        String errorMsg = "";
        //Fix below, takes an int not a string?
        //Product product = ProductDAO.get(productId);


        HttpSession session = req.getSession();
        session.setAttribute("flashMessageSuccess", "Added to Cart successfully");
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/shop"));
    }
}
