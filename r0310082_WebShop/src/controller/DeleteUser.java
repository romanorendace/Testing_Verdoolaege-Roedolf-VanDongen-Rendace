package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        getShopService().deletePerson(userId);
        request.setAttribute("persons", getShopService().getPersons());

        request.getSession().setAttribute("isRedirect", true);

        String destination = "personoverview.jsp";
        return destination;
    }
}
