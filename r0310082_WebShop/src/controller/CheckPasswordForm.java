package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswordForm extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("userid", request.getParameter("userId"));
        String destination = "checkPasswordForm.jsp";
        return destination;
    }
}
