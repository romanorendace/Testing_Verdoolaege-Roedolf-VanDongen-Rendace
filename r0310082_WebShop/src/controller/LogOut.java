package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("isLoggedIn", false);
        session.setAttribute("person", null);

        //session.invalidate();

        request.getSession().setAttribute("isRedirect", true);

        String destination = "index.jsp";
        return destination;
    }
}
