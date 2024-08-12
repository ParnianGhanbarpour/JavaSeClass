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
    private int price;
    private int count;
    private Brand brand;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
