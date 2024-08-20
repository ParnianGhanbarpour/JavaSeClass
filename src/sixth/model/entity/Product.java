package sixth.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class Product {
    private int id;
    private String name;
    private Brand brand;
    private int price;
    private int count;
    private Person owner;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
