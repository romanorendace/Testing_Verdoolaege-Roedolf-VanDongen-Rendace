package controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = getShopService().getPerson(request.getParameter("userid"));
        boolean isCorrectPassword = person.isCorrectPassword(request.getParameter("password"));
        String resultMessage = setCheckPasswordResultMessage(isCorrectPassword);

        request.setAttribute("resultMessage", resultMessage);
        String destination = "checkPasswordResult.jsp";
        return destination;
    }

    private String setCheckPasswordResultMessage(boolean isCorrectPassword) {
        if (!isCorrectPassword) {
            return "The password is NOT correct!";
        }
        return "The password is correct!";
    }
}
