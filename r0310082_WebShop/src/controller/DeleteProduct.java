package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        getShopService().deleteProduct(productId);
        request.setAttribute("products", getShopService().getProducts());
        String destination = "productoverview.jsp";
        return destination;
    }
}
