package fourth;


public abstract class House {
    private float height ;
    private int area ;
    private String  wallColor;
    private boolean woodenItemsExistence ;



    public float getHeight() {
        return height;
    }

    public int getArea() {
        return area;
    }


    public String getWallColor() {
        return wallColor;
    }

    public void setWallColor(String wallColor) {
        this.wallColor = wallColor;
    }

    public boolean isWoodenItemsExistence() {
        return woodenItemsExistence;
    }

    public void setWoodenItemsExistence(boolean woodenItemsExistence) {
        this.woodenItemsExistence = woodenItemsExistence;
    }

    @Override
    public String toString() {
        return "House{" +
                "height=" + height +
                ", area=" + area +
                ", wallColor='" + wallColor + '\'' +
                ", woodenItemsExistence=" + woodenItemsExistence +
                '}';
    }
}

