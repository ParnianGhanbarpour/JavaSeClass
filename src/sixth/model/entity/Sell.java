package sixth.model.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString


public class Sell {
    private int id;
    private String personUsername;
    private int productId;

    private double price;
    private LocalDateTime sellTime;

    private boolean deleted;
}

