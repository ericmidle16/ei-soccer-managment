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

@WebServlet("/cart")
public class Cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Cart");
        req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String productId = req.getParameter("product_id");
        Product product = ProductDAO.getCart(Integer.parseInt(productId));
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if(cart != null && product != null) {
            if (action.equals("update")) {
                String quantityStr = req.getParameter("quantity");
                int quantity = 1;
                try {
                    quantity = Integer.parseInt(quantityStr);
                    if (quantity < 1) {
                        quantity = cart.getContents().get(product);
                    }
                } catch (NumberFormatException e) {
                    // If error, keep whatever is in the cart
                    quantity = cart.getContents().get(product);
                }
                cart.updateProduct(product, quantity);
            } else if (action.equals("delete")) {
                cart.deleteProduct(product);
            }
            session.setAttribute("cart", cart);
        }
        req.setAttribute("pageTitle", "Cart");
        req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
    }
}
