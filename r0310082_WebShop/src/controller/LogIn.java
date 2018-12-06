package controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("password");

        if (isRegisteredUser(email, password)) {
            startUserSession(email, request);
        }
        else {

        }


        String destination = "index.jsp";
        return destination;
    }

    private boolean isRegisteredUser(String email, String password) {
        Person person = getShopService().getPersonByEmail(email);
        Boolean isRegisteredUser = person.isCorrectPassword(password);
        return isRegisteredUser;
    }

    private void startUserSession(String email, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("");
    }

}
