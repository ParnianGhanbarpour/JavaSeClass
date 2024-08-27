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
    private String username;
    private String password;
    private String name;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private int age;
    private boolean active;

    public int getAge() {
        return 2024 - birthDate.getYear();
    }
}
