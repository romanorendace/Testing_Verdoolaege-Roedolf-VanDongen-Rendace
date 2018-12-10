package controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Product;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        ArrayList<String> errors = new ArrayList<>();
        Product product = new Product();

        product.setProductId(getShopService().getNextProductId());
        getSetNameProduct(product, request, errors);
        getSetDescriptionProduct(product, request, errors);
        getSetPriceProduct(product, request, errors);

        if (errors.size() == 0) {
            try {
                getShopService().addProduct(product);

                request.getSession().setAttribute("isRedirect", true);

                request.getSession().setAttribute("products", getShopService().getProducts());

                String destination = "productoverview.jsp";
                return destination;
            }
            catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
            }
        }
        else {
            request.setAttribute("errors", errors);
        }
        String destination = "addProduct.jsp";
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
