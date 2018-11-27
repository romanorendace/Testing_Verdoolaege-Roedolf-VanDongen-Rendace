package controller;

import domain.model.Order;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Home extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        updateQuoteCookie(request, response);

        String destination = "index.jsp";
        return destination;
    }

    private void updateQuoteCookie(HttpServletRequest request, HttpServletResponse response) {
        String hasQuote = request.getParameter("quote");
        if (hasQuote != null) {
            Cookie cookie = getQuoteCookie(request);
            if (cookie != null) {
                cookie.setValue(hasQuote);
                response.addCookie(cookie);
            } else {
                Cookie firstCookie = new Cookie("quote", hasQuote);
                firstCookie.setMaxAge(-1);
                response.addCookie(firstCookie);
            }
            setQuoteFromValue(request, hasQuote);
        }
        else {
            setQuoteFromCookie(request);
        }
    }

    private void setQuoteFromValue(HttpServletRequest request, String value) {
        if (value.equals("yes")) {
            request.setAttribute("quote", "VENI, VIDI, VICI");
            request.setAttribute("yesChecked", "checked");
            request.setAttribute("noChecked", "");
        }
        else if (value.equals("no")) {
            request.setAttribute("quote", "");
            request.setAttribute("yesChecked", "");
            request.setAttribute("noChecked", "checked");
        }
    }

    private void setQuoteFromCookie(HttpServletRequest request) {
        Cookie cookie = getQuoteCookie(request);
        if (cookie != null) {
            String value = cookie.getValue();
            setQuoteFromValue(request, value);
        }
        else {
            request.setAttribute("quote", "");
            request.setAttribute("yesChecked", "");
            request.setAttribute("noChecked", "");
        }
    }

    private Cookie getQuoteCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c: cookies) {
                if (c.getName().equals("quote")) {
                    return c;
                }
            }
        }
        return null;
    }

}
