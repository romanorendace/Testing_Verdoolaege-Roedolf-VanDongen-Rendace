package controller;

import domain.model.ShopService;

public class ControllerFactory {

    public RequestHandler getController (String handlerName, ShopService service) { return createHandler(handlerName, service); }

    private RequestHandler createHandler (String handlerName, ShopService service) {
        RequestHandler handler = null;
        try {
            Class<?> handlerClass = Class.forName("controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setShopService(service);
        }
        catch (Exception e) {
            throw new RuntimeException("The requested page doesn't exist");
        }
        return handler;
    }
}
