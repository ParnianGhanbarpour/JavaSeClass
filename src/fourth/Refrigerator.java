package fourth;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Refrigerator extends Kitchen {

    private int numberOfFruitsVariety;

    public Refrigerator(int guaranteeOfElectricThings, boolean spoiledFood, int numberOfFruitsVariety) {
        super(guaranteeOfElectricThings, spoiledFood);
        this.numberOfFruitsVariety = numberOfFruitsVariety;
    }


    @Override
    public String toString() {
        return "Refrigerator{" +
                "numberOfFruitsVariety=" + numberOfFruitsVariety +
                '}';
    }
}

