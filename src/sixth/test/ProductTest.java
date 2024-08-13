package sixth.test;

import sixth.model.da.ProductDa;

import java.sql.SQLException;

public class ProductTest {
    public static void main(String[] args) throws Exception {
//        try with resource
        try(ProductDa personDa = new ProductDa()){
            ProductDa.save(null);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
