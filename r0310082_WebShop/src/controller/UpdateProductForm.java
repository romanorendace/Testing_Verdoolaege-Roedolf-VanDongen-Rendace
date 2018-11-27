package controller;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductForm extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        Product product = getShopService().getProduct(productId);

        request.setAttribute("product", product);
        String destination = "updateProductForm.jsp";
        return destination;
    }
}
