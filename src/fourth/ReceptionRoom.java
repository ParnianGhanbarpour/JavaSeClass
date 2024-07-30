package fourth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ReceptionRoom extends House {
    private int carpetArea;
    private String nameOfItems ;

    public ReceptionRoom(int carpetArea, String nameOfItems) {
        this.carpetArea = carpetArea;
        this.nameOfItems = nameOfItems;
    }



    @Override
    public String toString() {
        return "ReceptionRoom{" +
                "carpetArea=" + carpetArea +
                ", nameOfItems='" + nameOfItems + '\'' +
                '}';
    }
}

