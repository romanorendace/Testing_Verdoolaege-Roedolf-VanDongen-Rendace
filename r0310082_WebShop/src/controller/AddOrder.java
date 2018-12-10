package controller;

import domain.model.Order;
import domain.model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AddOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String productId = request.getParameter("orderProductId");
        int amount = Integer.parseInt(request.getParameter("orderAmount"));
        Order order = new Order(productId, amount);

        HttpSession session = request.getSession();

        List<Order> orders = (ArrayList) session.getAttribute("orders");

        boolean found = false;
        for (Order o : orders) {
            if (o.getProductId().equals(productId)) {
                o.setAmount(o.getAmount() + amount);
                found = true;
            }
        }
        if (!found) {
            orders.add(order);
        }


        session.setAttribute("orders", orders);
        session.setAttribute("products", getShopService().getProducts());
        Product product = getShopService().getProduct(productId);
        session.setAttribute("messageOrderConfirmed", product.getName() + " has been added to My Cart !");

        request.getSession().setAttribute("isRedirect", true);

        String destination = "productoverview.jsp";
        return destination;
    }


}
