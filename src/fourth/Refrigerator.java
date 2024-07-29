package fourth;

public class Refrigerator extends Kitchen {

    private int numberOfFruitsVariety;

    public Refrigerator(int guaranteeOfElectricThings, boolean spoiledFood, int numberOfFruitsVariety) {
        super(guaranteeOfElectricThings, spoiledFood);
        this.numberOfFruitsVariety = numberOfFruitsVariety;
    }

    public int getNumberOfFruitsVariety() {
        return numberOfFruitsVariety;
    }

    public void setNumberOfFruitsVariety(int numberOfFruitsVariety) {
        this.numberOfFruitsVariety = numberOfFruitsVariety;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "numberOfFruitsVariety=" + numberOfFruitsVariety +
                '}';
    }
}

