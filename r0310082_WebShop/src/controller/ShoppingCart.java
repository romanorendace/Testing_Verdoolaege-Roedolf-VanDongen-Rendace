package controller;

import domain.model.Order;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        //HttpSession session = request.getSession(false);
        HttpSession session = request.getSession();


        if (session.getAttribute("orders") == null) {
            session = request.getSession();
            session.setMaxInactiveInterval(60);
            session.setAttribute("orders", new ArrayList<Order>());
        }

        List<Order> orders = (ArrayList) session.getAttribute("orders");
        for (Order order : orders) {
            Product product = getShopService().getProduct(order.getProductId());
            order.setProduct(product);
        }
        session.setAttribute("orders", orders);

        String destination = "shoppingCart.jsp";
        return destination;
    }

}
