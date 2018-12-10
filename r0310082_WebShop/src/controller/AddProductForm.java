package controller;

import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProductForm extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        Role[] roles = {Role.ADMINISTRATOR};
        checkRole(request, roles);

        ArrayList<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        String destination = "addProduct.jsp";
        return destination;
    }

    private void checkRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        Person person = (Person) request.getSession().getAttribute("person");
        if (person != null) {
            for (Role role : roles) {
                if (person.getRole().equals(role)) {
                    found = true;
                }
            }
        }
        if (!found) { throw new NotAuthorizedException(); }
    }
}
