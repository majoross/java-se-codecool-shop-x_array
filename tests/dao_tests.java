import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by majoross on 2017.05.11..
 */
public class dao_tests {
    @Test
    public void invalidProductFindParameter() throws IllegalArgumentException{
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            productDaoMem.find(-1);
        });
    }

    @Test
    public void invalidSupplierFindParameter() throws  IllegalArgumentException{
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            supplierDaoMem.find(-1);
        });
    }

    @Test
    public void invalidProductCategoryFindParameter() throws IllegalArgumentException{
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            productCategoryDaoMem.find(-1);
        });
    }

    @Test
    public void invalidProductRemoveParameter() throws IllegalArgumentException{
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            productDaoMem.remove(-1);
        });
    }

    @Test
    public void invalidSupplierRemoveParameter() throws  IllegalArgumentException{
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            supplierDaoMem.remove(-1);
        });
    }

    @Test
    public void invalidProductCategoryRemoveParameter() throws IllegalArgumentException{
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()-> {
            productCategoryDaoMem.remove(-1);
        });
    }

    @Test
    public void productGetAllTest(){
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        List<Product> products = productDaoMem.getAll();
        assertEquals(9, products.size());

    }

    @Test
    public void supplierGetAllTest(){
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        List<Supplier> suppliers = supplierDaoMem.getAll();
        assertEquals(4, suppliers.size());
    }

    @Test
    public void productCategoryGetAllTest(){
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        List<ProductCategory> productCategories = productCategoryDaoMem.getAll();
        assertEquals(3, productCategories.size());
    }

}
