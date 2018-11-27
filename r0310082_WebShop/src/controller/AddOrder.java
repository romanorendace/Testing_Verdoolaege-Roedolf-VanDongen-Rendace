package controller;

import domain.model.Order;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String productId = request.getParameter("orderProductId");
        int amount = Integer.parseInt(request.getParameter("orderAmount"));
        Order order = new Order(productId, amount);

        HttpSession session = request.getSession(false);

        if (session.getAttribute("orders") == null) {
            session = request.getSession();
            session.setMaxInactiveInterval(60);
            session.setAttribute("orders", new ArrayList<Order>());
        }
        List<Order> orders = (ArrayList) session.getAttribute("orders");
        orders.add(order);

        session.setAttribute("orders", orders);


        String destination = "productoverview.jsp";
        return destination;
    }
}
