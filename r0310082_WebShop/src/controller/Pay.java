package controller;

import domain.model.Order;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Pay extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Person person = (Person) session.getAttribute("person");
        List<Order> orders =  (ArrayList) session.getAttribute("orders");
        getShopService().addPersonOrders(person, orders);

        session.setAttribute("orders", new ArrayList<>());
        session.setAttribute("isRedirect", true);

        String destination = "orderPayed.jsp";
        return destination;
    }
}
