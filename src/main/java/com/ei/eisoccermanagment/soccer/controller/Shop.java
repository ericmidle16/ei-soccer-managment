package com.ei.eisoccermanagment.soccer.controller;

import com.ei.eisoccermanagment.soccer.model.Product;
import com.ei.eisoccermanagment.soccer.model.ProductCategory;
import com.ei.eisoccermanagment.soccer.model.ProductColor;
import com.ei.eisoccermanagment.soccer.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/shop")
public class Shop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limitStr = req.getParameter("limit");
        int limit = 5;
        try{
            limit = Integer.parseInt(limitStr);
        } catch(NumberFormatException e) {

        }
        int offset = 0;
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if(categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        String[] colorsArr = req.getParameterValues("colors");
        String colors = "";
        if(colorsArr != null && colorsArr.length > 0) {
            colors = String.join(",", colorsArr);
        }
        req.setAttribute("colors", colors);
        req.setAttribute("categories", categories);
        req.setAttribute("limit", limit);
        List<Product> products = ProductDAO.getAll(limit, offset, categories, colors);
        // Attribute can be an OBJECT not a String
        req.setAttribute("products", products);
        List<ProductCategory> productCategories = ProductDAO.getAllCategories();
        req.setAttribute("productCategories", productCategories);
        List<ProductColor> productColors = ProductDAO.getAllColors();
        req.setAttribute("productColors", productColors);
        req.getRequestDispatcher("WEB-INF/shop.jsp").forward(req, resp);
    }
}