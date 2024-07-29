package fourth;

public class Kitchen extends House {
    private int guaranteeOfElectricThings;
    private boolean spoiledFood;

    public Kitchen(int guaranteeOfElectricThings, boolean spoiledFood) {
        this.guaranteeOfElectricThings = guaranteeOfElectricThings;
        this.spoiledFood = spoiledFood;
    }

    public int getGuaranteeOfElectricThings() {
        return guaranteeOfElectricThings;
    }

    public void setGuaranteeOfElectricThings(int guaranteeOfElectricThings) {
        this.guaranteeOfElectricThings = guaranteeOfElectricThings;
    }

    public boolean isSpoiledFood() {
        return spoiledFood;
    }

    public void setSpoiledFood(boolean spoiledFood) {
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