package sixth.model.bl;



import sixth.model.da.ProductDa;
import sixth.model.entity.Product;
import sixth.model.entity.Person;


public class ProductBl {
    public static void save(Product product) throws Exception {
        try (ProductDa productDa = new ProductDa()) {
            if (productDa.findCountByPersonId(product.getOwner().getId()) <5){
                productDa.save(product);
            }else{
                throw new Exception("there is no discount for you !!!");
            }
        }
    }
}
