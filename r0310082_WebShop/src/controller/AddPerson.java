package controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddPerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Person person = new Person();

        getSetUserIdPerson(person, request, errors);
        getSetFirstNamePerson(person, request, errors);
        getSetLastNamePerson(person, request, errors);
        getSetEmailPerson(person, request, errors);
        getSetPasswordPerson(person, request, errors);


        if (errors.size() == 0) {
            try {
                getShopService().addPerson(person);
                request.setAttribute("persons", getShopService().getPersons());
                String destination = "personoverview.jsp";
                return destination;
            }
            catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
            }
        }
        else {
            request.setAttribute("errors", errors);
        }
        String destination = "signUp.jsp";
        return destination;
    }


    private void getSetUserIdPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userId = request.getParameter("userid");
        try {
            person.setUserid(userId);
            request.setAttribute("userIdPreviousValue", userId);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetFirstNamePerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetLastNamePerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            person.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getSetEmailPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }



    private void getSetPasswordPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            person.setPasswordHashed(password);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }
}
