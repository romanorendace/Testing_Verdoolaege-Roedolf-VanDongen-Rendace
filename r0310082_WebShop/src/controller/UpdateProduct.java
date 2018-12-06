package controller;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class UpdateProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Product updatedProduct = new Product();

        getSetProductIdProduct(updatedProduct, request, errors);
        getSetNameProduct(updatedProduct, request, errors);
        getSetDescriptionProduct(updatedProduct, request, errors);
        getSetPriceProduct(updatedProduct, request, errors);

        if (errors.size() == 0) {
            try {
                getShopService().updateProduct(updatedProduct);
                request.setAttribute("products", getShopService().getProducts());
                return "productoverview.jsp";
            }
            catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
            }
        }
        else {
            request.setAttribute("errors", errors);
        }
        request.setAttribute("product", getShopService().getProduct(request.getParameter("productId")));
        String destination = "updateProductForm.jsp";
        return destination;
    }

    private void getSetProductIdProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String productId = request.getParameter("productId");
        try {
            product.setProductId(productId);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetNameProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        try {
            product.setName(name);
            request.setAttribute("namePreviousValue", name);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetDescriptionProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String description = request.getParameter("description");
        try {
            product.setDescription(description);
            request.setAttribute("descriptionPreviousValue", description);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetPriceProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String price = request.getParameter("price");
        try {
            product.setPrice(price);
            request.setAttribute("pricePreviousValue", price);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }
}
