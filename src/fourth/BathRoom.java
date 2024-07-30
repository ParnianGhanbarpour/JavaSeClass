package fourth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BathRoom extends BedRoom{
    private String colorOfMat;

    public BathRoom(int ledLight, String colorOfMat) {
        super(ledLight);
        this.colorOfMat = colorOfMat;
    }



    @Override
    public String toString() {
        return "BathRoom{" +
                "colorOfMat='" + colorOfMat + '\'' +
                '}';
    }
}
