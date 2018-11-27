package controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    private ShopService shopService;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public void setShopService (ShopService shopService) { this.shopService = shopService; }

    public ShopService getShopService () { return shopService; }
}
