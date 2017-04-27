import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Equivalent with above
        get("/index", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });

        //Shopping Cart
        get("/cart", ProductController::renderCart, new ThymeleafTemplateEngine());

        //Add to cart
        get("/add/:id", (Request req, Response res) -> {

            Product product = ProductDaoMem.getInstance().find(Integer.parseInt(req.params(":id")));
            ShoppingCartDaoMem.getInstance().add(product);

            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier hp = new Supplier("HP", "Laptops");
        supplierDataStore.add(hp);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop computer is a portable computer with a built-in keyboard");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(fire);
        Product ideaPad = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        productDataStore.add(ideaPad);
        Product fireHD = new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        productDataStore.add(fireHD);
        Product hpPavilion = new Product("HP Pavilion 5", 110, "USD", "HP's Pavilion product line's latest sensation", tablet, hp);
        productDataStore.add(hpPavilion);

        ShoppingCart cart1 = new ShoppingCart();
//        shoppingCartDataStore.add(fire);
//        shoppingCartDataStore.add(ideaPad);
//        shoppingCartDataStore.add(fireHD);
        //shoppingCartDataStore.add(hpPavilion);

    }


}
