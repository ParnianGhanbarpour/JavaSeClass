package fourth;

public class ReceptionRoom extends House {
    private int carpetArea;
    private String nameOfItems ;

    public ReceptionRoom(int carpetArea, String nameOfItems) {
        this.carpetArea = carpetArea;
        this.nameOfItems = nameOfItems;
    }

    public ReceptionRoom() {
    }

    public int getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(int carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getNameOfItems() {
        return nameOfItems;
    }

    public void setNameOfItems(String nameOfItems) {
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

