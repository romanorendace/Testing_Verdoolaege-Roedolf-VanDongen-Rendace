package controller;

import domain.model.Order;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        int newOrderAmount = Integer.parseInt(request.getParameter("newOrderAmount"));
        String productIdToUpdate = request.getParameter("productIdToUpdate");

        HttpSession session = request.getSession();
        List<Order> orders = (ArrayList) session.getAttribute("orders");

        for (Order order : orders) {
            if (order.getProductId().equals(productIdToUpdate)) {
                if (newOrderAmount == 0) {
                    orders.remove(order);
                    session.setAttribute("messageOrderUpdated", order.getProduct().getName() + " removed from order !");
                    break;
                }
                else {
                    order.setAmount(newOrderAmount);
                    session.setAttribute("messageOrderUpdated", order.getProduct().getName() + " amount updated !");
                }
            }
        }

        double totalPrice = 0.0;
        for (Order order : orders) {
            totalPrice += order.getProduct().getPrice() * order.getAmount();
        }
        totalPrice *= 100;
        totalPrice = Math.round(totalPrice);
        totalPrice /= 100;
        session.setAttribute("totalPrice", totalPrice);

        request.getSession().setAttribute("isRedirect", true);

        String destination = "shoppingCart.jsp";
        return destination;
    }
}
