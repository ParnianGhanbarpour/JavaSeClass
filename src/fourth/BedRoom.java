package fourth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BedRoom extends House {
    private int ledLight;

    public BedRoom(int ledLight) {
        this.ledLight = ledLight;
    }

}

