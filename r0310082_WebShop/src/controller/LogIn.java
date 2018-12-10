package controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email").trim().toLowerCase();
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            session.setAttribute("messageLogInFailed", "Log in unsuccessful, please enter valid email/password" );
        }
        else {
            Person person = getShopService().getPersonByEmail(email);
            if (person.isCorrectPassword(password)) {
                session.setAttribute("isLoggedIn", true);
                session.setAttribute("person", person);
            }
            else {
                session.setAttribute("messageLogInFailed", "Log in unsuccessful, please enter valid email/password" );
            }
        }

        request.getSession().setAttribute("isRedirect", true);

        String destination = "index.jsp";
        return destination;
    }


}
