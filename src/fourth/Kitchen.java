package fourth;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Kitchen extends House {
    private int guaranteeOfElectricThings;
    @Getter
    private boolean spoiledFood;

    public Kitchen(int guaranteeOfElectricThings, boolean spoiledFood) {
        this.guaranteeOfElectricThings = guaranteeOfElectricThings;
        this.spoiledFood = spoiledFood;
    }


    @Override
    public String toString() {
        return "Kitchen{" +
                "guaranteeOfElectricThings=" + guaranteeOfElectricThings +
                ", spoiledFood=" + spoiledFood +
                '}';
    }
}