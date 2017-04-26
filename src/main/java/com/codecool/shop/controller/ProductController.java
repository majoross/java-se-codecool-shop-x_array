package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static ProductDaoMem productDataStore;
    private static ProductCategoryDao productCategoryDataStore;
    private static SupplierDao supplierDataStore;
    private static Map params;

    //rendering all products
    public static ModelAndView renderProducts(Request req, Response res) {
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();

        params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    //rendering products based on category
    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();
        String categoryName = req.params(":name");
        ProductCategory categoryToFilter = productCategoryDataStore.find(categoryName);

        params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(categoryToFilter));
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    //rendering products based on supplier
    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();
        String supplierName = req.params(":name");
        Supplier supplierToFilter = supplierDataStore.find(supplierName);

        params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(supplierToFilter));
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }
}