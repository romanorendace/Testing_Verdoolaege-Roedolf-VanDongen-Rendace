package controller;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        getShopService().deleteProduct(productId);

        request.getSession().setAttribute("isRedirect", true);

        request.getSession().setAttribute("products", getShopService().getProducts());

        String destination = "productoverview.jsp";
        return destination;
    }
}
