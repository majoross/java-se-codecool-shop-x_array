package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private static ProductDaoMem productDataStore;
    private static ProductCategoryDao productCategoryDataStore;
    private static SupplierDao supplierDataStore;
    private static Map params;
    private static ProductCategory categoryToFilter;
    private static Supplier supplierToFilter;
    private static List<Product> filteredProducts;

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

    //rendering products based on a filter
    public static ModelAndView renderProductsWithFilter(Request req, Response res) {
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();

        String targetName = req.params(":name");
        int targetId;

        //finding supplier/category id with :name

        for (ProductCategory cat : productCategoryDataStore.getAll()) {
            if (targetName.equals(cat.getName())) {
                targetId = cat.getId();
                categoryToFilter = productCategoryDataStore.find(targetId);
            }
        }
        for (Supplier sup : supplierDataStore.getAll()) {
            if (targetName.equals(sup.getName())) {
                targetId = sup.getId();
                supplierToFilter = supplierDataStore.find(targetId);
            }
        }

        //filling up the filtered list from either supplier or category

        if (supplierToFilter != null) {

            filteredProducts = supplierToFilter.getProducts();

        } else if (categoryToFilter != null) {

            filteredProducts = categoryToFilter.getProducts();
        }

        params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", filteredProducts);
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }
}