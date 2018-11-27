package controller;

import domain.model.Person;
import domain.model.comparator.PersonComparatorFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class Users extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        updateSortCookie(request, response);
        String destination = "personoverview.jsp";
        return destination;
    }

    private void updateSortCookie(HttpServletRequest request, HttpServletResponse response) {
        String value = request.getParameter("sort");
        if (value != null) {
            Cookie sortCookie = getSortCookie(request);
            if (sortCookie != null) {
                sortCookie.setValue(value);
                response.addCookie(sortCookie);
            } else {
                Cookie firstSortCookie = new Cookie("sort", value);
                firstSortCookie.setMaxAge(-1);
                response.addCookie(firstSortCookie);
            }
            setSortPreferenceFromValue(request, value);
            sortPersonsListFromValue(request, value);
        } else {
            setSortPreferenceFromCookie(request);
            sortPersonsListFromCookie(request);
        }

    }

    private void setSortPreferenceFromValue(HttpServletRequest request, String value) {
        if (value != null) {
            if (value.equals("email")) {
                request.setAttribute("sortEmail", "selected");
                request.setAttribute("sortFirstname", "");
                request.setAttribute("sortLastname", "");
            } else if (value.equals("firstname")) {
                request.setAttribute("sortEmail", "");
                request.setAttribute("sortFirstname", "selected");
                request.setAttribute("sortLastname", "");
            } else if (value.equals("lastname")) {
                request.setAttribute("sortEmail", "");
                request.setAttribute("sortFirstname", "");
                request.setAttribute("sortLastname", "selected");
            }
        } else {
            request.setAttribute("sortEmail", "");
            request.setAttribute("sortFirstname", "");
            request.setAttribute("sortLastname", "");
        }
    }

    private void setSortPreferenceFromCookie(HttpServletRequest request) {
        Cookie sortCookie = getSortCookie(request);
        if (sortCookie != null) {
            String sortValue = sortCookie.getValue();
            setSortPreferenceFromValue(request, sortValue);
        } else {
            setSortPreferenceFromValue(request, null);
        }
    }

    private void sortPersonsListFromValue(HttpServletRequest request, String value) {
        List<Person> persons = getShopService().getPersons();
        PersonComparatorFactory factory = new PersonComparatorFactory();
        String sortKey = value;
        Comparator<Person> comparator = factory.createPersonComparator(value);
        persons.sort(comparator);
        request.setAttribute("persons", persons);
    }

    private void sortPersonsListFromCookie(HttpServletRequest request) {
        List<Person> persons = getShopService().getPersons();
        Cookie sortCookie = getSortCookie(request);
        if (sortCookie != null) {
            PersonComparatorFactory factory = new PersonComparatorFactory();
            String sortKey = sortCookie.getValue();
            Comparator<Person> comparator = factory.createPersonComparator(sortKey);
            persons.sort(comparator);
        }
        request.setAttribute("persons", persons);
    }

    private Cookie getSortCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sort")) {
                    return cookie;
                }
            }
        }
        return null;
    }
}