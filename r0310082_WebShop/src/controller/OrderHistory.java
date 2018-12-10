package controller;

import domain.model.Order;
import domain.model.Person;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderHistory extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Person person = (Person) session.getAttribute("person");
        List<Order> orderHistoryList = getShopService().getPersonOrders(person);

        for (Order order : orderHistoryList) {
            Product product = getShopService().getProduct(order.getProductId());
            order.setProduct(product);
        }

        session.setAttribute("orderHistoryList", orderHistoryList);

        String destination = "orderHistory.jsp";
        return destination;
    }
}
