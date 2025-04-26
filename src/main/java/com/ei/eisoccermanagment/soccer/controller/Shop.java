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
        // Get the limit (number of products to show)
        String limitStr = req.getParameter("limit");
        int limit = 5;
        try{
            limit = Integer.parseInt(limitStr);
        } catch(NumberFormatException e) {

        }
        req.setAttribute("limit", limit);

        // Get categories
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if(categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        req.setAttribute("categories", categories);
        req.setAttribute("categoriesArr", categoriesArr);

        // Get colors
        String[] colorsArr = req.getParameterValues("colors");
        String colors = "";
        if(colorsArr != null && colorsArr.length > 0) {
            colors = String.join(",", colorsArr);
        }
        req.setAttribute("colors", colors);
        req.setAttribute("colorsArr", colorsArr);

        // Get the total products
        int totalProducts = ProductDAO.getProductCount(categories, colors);
        int totalPages = totalProducts / limit;
        if(totalProducts % limit != 0) {
            totalPages++;
        }
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("totalPages", totalPages);

        // Get the page number
        String pageStr = req.getParameter("page");
        int page = 1;
        try{
            page = Integer.parseInt(pageStr);
        } catch(NumberFormatException e) {

        }
        if(page > totalPages) {
            page = totalPages;
        } else if(page < 1) {
            page = 1;
        }
        req.setAttribute("page", page);
        int offset = (page - 1) * limit;

        // Calculate begin and end page links
        int pageLinks = 5;
        int beginPage = page / pageLinks * pageLinks > 0 ? page / pageLinks * pageLinks : 1;
        int endPage = beginPage + pageLinks - 1 > totalPages ? totalPages : beginPage + pageLinks - 1;
        req.setAttribute("beginPage", beginPage);
        req.setAttribute("endPage", endPage);

        // Determine first and last products shown
        int firstProductShown = 1 + (page - 1) * limit;
        req.setAttribute("firstProductShown", firstProductShown);

        int lastProductShown = limit + (page - 1) * limit;
        if(lastProductShown > totalProducts) {
            lastProductShown = totalProducts;
        }
        req.setAttribute("lastProductShown", lastProductShown);

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