package com.ei.eisoccermanagment.soccer.controller;

import com.ei.eisoccermanagment.soccer.model.Product;
import com.ei.eisoccermanagment.soccer.model.ProductDAO;
import com.ei.eisoccermanagment.soccer.model.ShoppingCart;
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
        String productId = req.getParameter("product_id");
        String quantityStr = req.getParameter("quantity");

        boolean errorFound = false;
        String errorMsg = "";
        Product product = ProductDAO.getCart(Integer.parseInt(productId));
        if(product == null){
            errorFound = true;
            errorMsg += "Invalid product\n";
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if(quantity < 0){
                errorFound = true;
                errorMsg += "Quantity cannot be negative\n";
            }
        } catch (NumberFormatException e) {
            errorFound = true;
            errorMsg += "Invalid quantity";
        }

        HttpSession session = req.getSession();
        if(errorFound) {
            session.setAttribute("flashMessageDanger", errorMsg);
        } else {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }
            cart.addProduct(product, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("flashMessageSuccess", "Added to cart successfully");
        }

        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/shop"));
    }
}
