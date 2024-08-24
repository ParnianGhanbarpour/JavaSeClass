package sixth.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Person {
    private int id;
    private String name ;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private List<Product>productList;
    private int  age  =2024- birthDate.getYear();


}
