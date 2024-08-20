package sixth.test;

import sixth.model.da.PersonDa;
import sixth.model.da.ProductDa;
import sixth.model.entity.Person;

import java.sql.SQLException;

public class ProductTest {
    public static void main(String[] args) throws Exception {
//        try with resource
        try(ProductDa productDa = new ProductDa()){
            productDa.save(null);
            productDa.edit(null);
            productDa.remove(0);

            Person person = null;
            try (PersonDa personDa = new PersonDa()) {
                person = personDa.findById(4);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
