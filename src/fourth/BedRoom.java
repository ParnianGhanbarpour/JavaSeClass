package fourth;

public class BedRoom extends House {
    private int ledLight;

    public BedRoom(int ledLight) {
        this.ledLight = ledLight;
    }

    public int getLedLight() {
        return ledLight;
    }

    public void setLedLight(int ledLight) {
        this.ledLight = ledLight;
    }

    @Override
    public String toString() {
        return "BedRoom{" +
                "ledLight=" + ledLight +
                '}';
    }
}

