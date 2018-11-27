package controller;

import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

@WebServlet("/Controller")
public class WebShopServlet extends HttpServlet {

    private ShopService shopService; // = new ShopService();
    private ControllerFactory controllerFactory = new ControllerFactory();

    public WebShopServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        Properties properties = new Properties();
        Enumeration<String> paramNames = context.getInitParameterNames();
        while (paramNames.hasMoreElements()) {
            String propertyName = paramNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }
        this.shopService = new ShopService(properties);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String destination = "index.jsp";
        if (action != null) {
                action = action.substring(0, 1).toUpperCase() + action.substring(1); // set first char to Uppercase for mapping with Classes
                RequestHandler handler;
                handler = controllerFactory.getController(action , shopService);
            destination = handler.handleRequest(request, response);

        }
        request.getRequestDispatcher(destination).forward(request, response);




//        String action = "home";
//        if (request.getParameter("action") != null) {
//            action = request.getParameter("action");
//        }
//
//        String destination;
//        switch (action) {
//            case "home":
//                destination = home(request, response); break;
//            case "users":
//                destination = users(request, response); break;
//            case "addPerson":
//                destination = addPerson(request, response); break;
//            case "deleteUserForm":
//                destination = deleteUserForm(request, response); break;
//            case "deleteUser":
//                destination = deleteUser(request, response); break;
//            case "checkPasswordForm":
//                destination = checkPasswordForm(request, response); break;
//            case "checkPassword":
//                destination = checkPassword(request, response); break;
//            case "products":
//                destination = products(request, response); break;
//            case "addProductForm":
//                destination = addProductForm(request, response); break;
//            case "addProduct":
//                destination = addProduct(request, response); break;
//            case "updateProductForm":
//                destination = updateProductForm(request, response); break;
//            case "updateProduct":
//                destination = updateProduct(request, response); break;
//            case "deleteProductForm":
//                destination = deleteProductForm(request, response); break;
//            case "deleteProduct":
//                destination = deleteProduct(request, response); break;
//            case "signUp":
//                destination = signUp(request, response); break;
//            default:
//                destination = home(request, response);
//        }
//        request.getRequestDispatcher(destination).forward(request, response);
    }
//    private String home(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String destination = "index.jsp";
//        return destination;
//    }

//    private String users(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("persons", this.shopService.getPersons());
//        String destination = "personoverview.jsp";
//        return destination;
//    }

//    private String products(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("products", this.shopService.getProducts());
//        String destination = "productoverview.jsp";
//        return destination;
//    }

//    private String addProductForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<String> errors = new ArrayList<>();
//        request.setAttribute("errors", errors);
//        String destination = "addProduct.jsp";
//        return destination;
//    }

//    private String addProduct(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<String> errors = new ArrayList<>();
//        Product product = new Product();
//
//        product.setProductId(shopService.getNextProductId());
//        getSetNameProduct(product, request, errors);
//        getSetDescriptionProduct(product, request, errors);
//        getSetPriceProduct(product, request, errors);
//
//        if (errors.size() == 0) {
//            try {
//                shopService.addProduct(product);
//                return products(request, response);
//            }
//            catch (Exception exc) {
//                request.setAttribute("error", exc.getMessage());
//            }
//        }
//        else {
//            request.setAttribute("errors", errors);
//        }
//        String destination = "addProduct.jsp";
//        return destination;
//    }

//    private String updateProductForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String productId = request.getParameter("productId");
//        Product product = shopService.getProduct(productId);
//
//        request.setAttribute("product", product);
//        String destination = "updateProductForm.jsp";
//        return destination;
//    }

//    private String updateProduct(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<String> errors = new ArrayList<>();
//        Product updatedProduct = new Product();
//
//        getSetProductIdProduct(updatedProduct, request, errors);
//        getSetNameProduct(updatedProduct, request, errors);
//        getSetDescriptionProduct(updatedProduct, request, errors);
//        getSetPriceProduct(updatedProduct, request, errors);
//
//        if (errors.size() == 0) {
//            try {
//                shopService.updateProduct(updatedProduct);
//                return products(request, response);
//            }
//            catch (Exception exc) {
//                request.setAttribute("error", exc.getMessage());
//            }
//        }
//        else {
//            request.setAttribute("errors", errors);
//        }
//        request.setAttribute("product", this.shopService.getProduct(request.getParameter("productId")));
//        String destination = "updateProductForm.jsp";
//        return destination;
//    }

//    private String deleteProductForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("productId", request.getParameter("productId"));
//        String destination = "deleteProductForm.jsp";
//        return destination;
//    }

//    private String deleteProduct(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String productId = request.getParameter("productId");
//        this.shopService.deleteProduct(productId);
//        String destination = products(request, response);
//        return destination;
//    }

//    private String signUp(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<String> errors = new ArrayList<>();
//        request.setAttribute("errors", errors);
//        String destination = "signUp.jsp";
//        return destination;
//    }

//    private String addPerson (HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<String> errors = new ArrayList<>();
//        Person person = new Person();
//
//        getSetUserIdPerson(person, request, errors);
//        getSetFirstNamePerson(person, request, errors);
//        getSetLastNamePerson(person, request, errors);
//        getSetEmailPerson(person, request, errors);
//        getSetPasswordPerson(person, request, errors);
//
//
//        if (errors.size() == 0) {
//            try {
//                this.shopService.addPerson(person);
//                return users(request, response);
//        }
//            catch (Exception exc) {
//                request.setAttribute("error", exc.getMessage());
//            }
//        }
//        else {
//            request.setAttribute("errors", errors);
//        }
//        String destination = "signUp.jsp";
//        return destination;
//    }

//    private String deleteUserForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("userId", request.getParameter("userId"));
//        String destination = "deletePersonForm.jsp";
//        return destination;
//    }

//    private String deleteUser(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String userId = request.getParameter("userId");
//        shopService.deletePerson(userId);
//        String destination = users(request, response);
//        return destination;
//
//    }

//    private String checkPasswordForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("userid", request.getParameter("userId"));
//        String destination = "checkPasswordForm.jsp";
//        return destination;
//    }

//    private String checkPassword(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println(request.getParameter("userid"));
//        Person person = shopService.getPerson(request.getParameter("userid"));
//        boolean isCorrectPassword = person.isCorrectPassword(request.getParameter("password"));
//        String resultMessage = setCheckPasswordResultMessage(isCorrectPassword);
//
//        request.setAttribute("resultMessage", resultMessage);
//        String destination = "checkPasswordResult.jsp";
//        return destination;
//    }
//
//    private String setCheckPasswordResultMessage(boolean isCorrectPassword) {
//        if (!isCorrectPassword) {
//            return "The password is NOT correct!";
//        }
//        return "The password is correct!";
//    }


    // GET SET PERSON METHODS
//    private void getSetUserIdPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
//        String userId = request.getParameter("userid");
//        try {
//            person.setUserid(userId);
//            request.setAttribute("userIdPreviousValue", userId);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetFirstNamePerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
//        String firstName = request.getParameter("firstName");
//        try {
//            person.setFirstName(firstName);
//            request.setAttribute("firstNamePreviousValue", firstName);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetLastNamePerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
//        String lastName = request.getParameter("lastName");
//        try {
//            person.setLastName(lastName);
//            request.setAttribute("lastNamePreviousValue", lastName);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetEmailPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
//        String email = request.getParameter("email");
//        try {
//            person.setEmail(email);
//            request.setAttribute("emailPreviousValue", email);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//
//
//    private void getSetPasswordPerson(Person person, HttpServletRequest request, ArrayList<String> errors) {
//        String password = request.getParameter("password");
//        try {
//            person.setPasswordHashed(password);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }

    // GET SET PRODUCT
//    private void getSetProductIdProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
//        String productId = request.getParameter("productId");
//        try {
//            product.setProductId(productId);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetNameProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
//        String name = request.getParameter("name");
//        try {
//            product.setName(name);
//            request.setAttribute("namePreviousValue", name);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetDescriptionProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
//        String description = request.getParameter("description");
//        try {
//            product.setDescription(description);
//            request.setAttribute("descriptionPreviousValue", description);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
//
//    private void getSetPriceProduct(Product product, HttpServletRequest request, ArrayList<String> errors) {
//        String price = request.getParameter("price");
//        try {
//            product.setPrice(price);
//            request.setAttribute("pricePreviousValue", price);
//        }
//        catch (Exception exc) {
//            errors.add(exc.getMessage());
//        }
//    }
}
